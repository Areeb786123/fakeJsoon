package com.areeb.fakejsoon.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.models.common.UserModel
import com.areeb.fakejsoon.data.network.local.PersonDao
import com.areeb.fakejsoon.ui.home.pagination.PaginationSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteOperation: RemoteOperation,
    private val personDao: PersonDao,
) : ViewModel() {
    companion object {
        private const val TAG = "homeViewModel"
    }

    private val _user = MutableLiveData<PagingData<UserModel>>()
    val user: LiveData<PagingData<UserModel>>
        get() = _user

    private fun getAllUsersFromServer(): Flow<PagingData<UserModel>> {
        return Pager(
            config = PagingConfig(pageSize = 1, maxSize = 20),
            pagingSourceFactory = { PaginationSource(remoteOperation, personDao) },
        ).flow.cachedIn(viewModelScope)
    }

    fun setUser() {
        viewModelScope.launch {
            getAllUsersFromServer().collectLatest {
                _user.value = it
            }
        }
    }
}
