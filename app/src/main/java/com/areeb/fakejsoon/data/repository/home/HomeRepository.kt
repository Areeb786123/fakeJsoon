package com.areeb.fakejsoon.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.ui.home.pagination.PaginationSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteOperation: RemoteOperation,
) {
    fun getAllUsers(scope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            PaginationSource(remoteOperation)
        },
    ).flow.cachedIn(scope)
}
