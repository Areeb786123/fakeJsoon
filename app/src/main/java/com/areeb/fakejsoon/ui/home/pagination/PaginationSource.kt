package com.areeb.fakejsoon.ui.home.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.data.network.remote.api.home.HomeApi

class PaginationSource(private val homeApi: HomeApi) :
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
            val response = homeApi.getUsers(position)
            Log.e("getUserData", response.toString())
            return LoadResult.Page(
                data = response.data,
                prevKey = position,
                nextKey = position + 1,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
