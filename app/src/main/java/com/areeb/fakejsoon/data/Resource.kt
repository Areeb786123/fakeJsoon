package com.areeb.fakejsoon.data

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()

    data class Error(
        val errorMessage: String,
    ) : Resource<Nothing>()

    data class Loading(
        val status: Boolean? = null,
        val tags: String? = null,
    ) :
        Resource<Nothing>()
}
