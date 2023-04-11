package com.areeb.fakejsoon.data.repository.home

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.network.local.PersonDao
import com.areeb.fakejsoon.ui.home.pagination.PaginationSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteOperation: RemoteOperation,
    private val personDao: PersonDao,
) {
    fun getAllUsers(scope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            PaginationSource(remoteOperation, personDao)
        },
    ).flow.cachedIn(scope)

    suspend fun getDbData() {
        val response = personDao.getAllUsers()
        Log.e(
            "ROOMY",
            response.toString(),
        )
    }
}
