package com.areeb.fakejsoon.data

import com.areeb.fakejsoon.data.models.UserResponseDto
import com.areeb.fakejsoon.data.postModels.post.PostUserDto
import retrofit2.http.Body

interface IRemoteOperation {
    suspend fun getAllUsers(page: Int): Resource<UserResponseDto>

    suspend fun postUser(@Body postUser: PostUserDto): PostUserDto
}
