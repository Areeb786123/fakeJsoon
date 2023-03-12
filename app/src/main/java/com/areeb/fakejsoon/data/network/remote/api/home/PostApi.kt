package com.areeb.fakejsoon.data.network.remote.api.home

import com.areeb.fakejsoon.data.postModels.post.PostUserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PostApi {

    @POST("users")
    suspend fun postUser(@Body userPostBody: PostUserDto): PostUserDto
}
