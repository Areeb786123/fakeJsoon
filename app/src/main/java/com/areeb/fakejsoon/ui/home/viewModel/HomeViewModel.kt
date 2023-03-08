package com.areeb.fakejsoon.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    companion object {
        private const val TAG = "homeViewModel"
    }

    private val _user = MutableLiveData<PagingData<Data>>()
    val user: LiveData<PagingData<Data>>
        get() = _user

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers(viewModelScope).catch {
                Log.e(TAG, it.toString())
            }.collect {
                Log.e(
                    "userData",
                    it.toString(),
                )
                _user.value = it
//                setUserResponseFromServer(it)
            }
        }
    }

//    private fun setUserResponseFromServer(response: Resource<UserResponseDto>) {
//        if (response is Resource.Success) {
//            _user.value = response.data.data
//        }
//    }
}
