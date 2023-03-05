package com.areeb.fakejsoon.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers().catch {
                Log.e(TAG, it.toString())
            }.collect {
                Log.e(
                    "userData",
                    it.toString(),
                )
            }
        }
    }
}
