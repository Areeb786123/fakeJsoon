package com.areeb.fakejsoon.data.network.remote.api.home

import com.areeb.fakejsoon.data.models.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UserResponseDto
}
