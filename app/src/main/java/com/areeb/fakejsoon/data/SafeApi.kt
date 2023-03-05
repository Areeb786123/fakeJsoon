package com.areeb.fakejsoon.data

interface SafeApi {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Resource.Error(
                throwable.message.toString(),
            )
        }
    }
}
