package com.areeb.fakejsoon.ui.home.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.PrimaryKey
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.Resource
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.data.network.local.PersonDao
import com.areeb.fakejsoon.data.network.local.PersonEntity
import org.intellij.lang.annotations.PrintFormat

class PaginationSource(
    private val remoteOperation: RemoteOperation,
    private val personDao: PersonDao,
) :
    PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition.let {
            val anchorPage = it?.let { position -> state.closestPageToPosition(position) }
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            val position = params.key ?: 1
            val response = remoteOperation.getAllUsers(position)

            return if (response is Resource.Success) {
                // Insert the received data into the local database
                val personList = mutableListOf<PersonEntity>()
                response.data.data.forEach { data ->
                    val personEntity = PersonEntity(
                        id = data.id,
                        avatar = data.avatar,
                        first_name = data.first_name,
                        last_name = data.last_name,
                    )
                    personList.add(personEntity)
                }
                personDao.insertUser(personList)
                Log.e("DMdATA", personDao.getAllUsers().toString())
                LoadResult.Page(
                    data = response.data.data,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (response.data.data.isEmpty()) {
                        null
                    } else {
                        position + 1
                    },
                )
            } else {
                LoadResult.Error(java.lang.Exception("some error occur while fetching the data"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
