package com.areeb.fakejsoon.data.repository.home

import com.areeb.fakejsoon.data.RemoteOperation
import com.areeb.fakejsoon.data.Resource
import com.areeb.fakejsoon.data.models.UserResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteOperation: RemoteOperation,
) {
    fun getAllUsers(): Flow<Resource<UserResponseDto>> {
        return flow {
            val userResponse = remoteOperation.getAllUsers()
            emit(userResponse)
        }.flowOn(Dispatchers.IO)
    }
}
