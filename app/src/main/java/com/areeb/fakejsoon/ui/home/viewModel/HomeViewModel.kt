package com.areeb.fakejsoon.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.fakejsoon.data.Resource
import com.areeb.fakejsoon.data.models.Data
import com.areeb.fakejsoon.data.models.UserResponseDto
import com.areeb.fakejsoon.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    companion object {
        private const val TAG = "homeViewModel"
    }

    private val _user = MutableLiveData<List<Data>>()
    val user: LiveData<List<Data>>
        get() = _user

    init {
        getAllUsers()
    }
    private fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers().catch {
                Log.e(TAG, it.toString())
            }.collect {
                Log.e(
                    "userData",
                    it.toString(),
                )
                setUserResponseFromServer(it)
            }
        }
    }

    private fun setUserResponseFromServer(response: Resource<UserResponseDto>) {
        if (response is Resource.Success) {
            _user.value = response.data.data
        }

    }
}
