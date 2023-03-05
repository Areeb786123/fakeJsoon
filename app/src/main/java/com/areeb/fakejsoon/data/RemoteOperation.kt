package com.areeb.fakejsoon.data

import com.areeb.fakejsoon.data.models.UserResponseDto
import com.areeb.fakejsoon.data.network.remote.api.home.HomeApi
import javax.inject.Inject

class RemoteOperation @Inject constructor(private val homeApi: HomeApi) :
    IRemoteOperation,
    SafeApi {
    override suspend fun getAllUsers(): Resource<UserResponseDto> {
        return safeApiCall { homeApi.getUsers() }
    }
}
