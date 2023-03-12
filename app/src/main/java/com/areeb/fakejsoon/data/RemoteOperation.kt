package com.areeb.fakejsoon.data

import com.areeb.fakejsoon.data.models.UserResponseDto
import com.areeb.fakejsoon.data.network.remote.api.home.HomeApi
import com.areeb.fakejsoon.data.network.remote.api.home.PostApi
import com.areeb.fakejsoon.data.postModels.post.PostUserDto
import javax.inject.Inject

class RemoteOperation @Inject constructor(
    private val homeApi: HomeApi,
    private val postApi: PostApi,
) :
    IRemoteOperation,
    SafeApi {
    override suspend fun getAllUsers(page: Int): Resource<UserResponseDto> {
        return safeApiCall { homeApi.getUsers(page) }
    }

    override suspend fun postUser(postUser: PostUserDto): PostUserDto {
        return postApi.postUser(postUser)
    }
}
