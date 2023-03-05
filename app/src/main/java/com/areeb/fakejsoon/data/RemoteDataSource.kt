package com.areeb.fakejsoon.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    fun <Api> buildApi(baseUrl: String, api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
