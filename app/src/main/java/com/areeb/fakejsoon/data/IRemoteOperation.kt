package com.areeb.fakejsoon.data

import com.areeb.fakejsoon.data.models.UserResponseDto

interface IRemoteOperation {
    suspend fun getAllUsers(page: Int): Resource<UserResponseDto>
}
