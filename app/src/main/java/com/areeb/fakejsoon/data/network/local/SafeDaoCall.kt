package com.areeb.fakejsoon.data.network.local

interface SafeDaoCall {
    suspend fun <T> safeDaoApiCall(daoCall: suspend () -> T): DbResult<T> {
        return DbResult(daoCall.invoke())
    }
}
