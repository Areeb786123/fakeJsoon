package com.areeb.fakejsoon.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.postModels.post.PostUserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val remoteOperation: RemoteOperation) :
    ViewModel() {

    fun postUser(name: String, job: String) {
        val dto = PostUserDto(name = name, job = job)
        viewModelScope.launch {
            remoteOperation.postUser(dto)
        }
    }
}
