package com.areeb.fakejsoon.ui.home.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.Resource
import com.areeb.fakejsoon.data.models.Data

class PaginationSource(private val remoteOperation: RemoteOperation) :
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
            Log.e("getUserData", response.toString())
            return if (response is Resource.Success) {
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
