package com.areeb.fakejsoon.data.network.remote.api.home

import com.areeb.fakejsoon.data.models.UserResponseDto
import retrofit2.http.GET

interface HomeApi {

    @GET("users?page=2")
    suspend fun getUsers(): UserResponseDto
}
