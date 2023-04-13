package com.areeb.fakejsoon.ui.home.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.Resource
import com.areeb.fakejsoon.data.models.common.UserModel
import com.areeb.fakejsoon.data.network.local.PersonDao
import com.areeb.fakejsoon.data.network.local.PersonEntity

class PaginationSource(
    private val remoteOperation: RemoteOperation,
    private val personDao: PersonDao,
) :
    PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition.let {
            val anchorPage = it?.let { position -> state.closestPageToPosition(position) }
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        try {
            val position = params.key ?: 1
            val response = remoteOperation.getAllUsers(position)
            val localData = personDao.getAllUsers()

            return if (response is Resource.Success) {
                // Insert the received data into the local database
                val personList = mutableListOf<PersonEntity>()
                val userModel = mutableListOf<UserModel>()
                response.data.data.forEach { data ->
                    if (!localData.any { it.id == data.id }) {
                        val personEntity = PersonEntity(
                            id = data.id,
                            email = data.email,
                            avatar = data.avatar,
                            first_name = data.first_name,
                            last_name = data.last_name,
                        )
                        personList.add(personEntity)
                    }

                    val userModelDto = UserModel(
                        id = data.id,
                        email = data.email,
                        avatar = data.avatar,
                        first_name = data.first_name,
                        last_name = data.last_name,
                    )
                    userModel.add(userModelDto)
                }
                personDao.insertUser(personList)
                Log.e("DMdATA", personDao.getAllUsers().toString())

                LoadResult.Page(
                    data = userModel,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (response.data.data.isEmpty()) {
                        null
                    } else {
                        position + 1
                    },
                )
            } else {
                val userModel = mutableListOf<UserModel>()
                if (localData.isNotEmpty()) {
                    localData.forEach {
                        val userModelDto = UserModel(
                            id = it.id,
                            email = it.email,
                            first_name = it.first_name,
                            last_name = it.last_name,
                            avatar = it.avatar,
                        )
                        userModel.add(userModelDto)
                    }

                    LoadResult.Page(
                        data = userModel,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (localData.isEmpty()) {
                            null
                        } else {
                            position + 1
                        },
                    )
                } else {
                    LoadResult.Error(java.lang.Exception("some error occur while fetching the data"))
                }
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
