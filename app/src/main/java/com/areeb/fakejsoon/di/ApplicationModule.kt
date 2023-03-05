package com.areeb.fakejsoon.di

import com.areeb.fakejsoon.data.RemoteDataSource
import com.areeb.fakejsoon.data.network.remote.api.home.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Singleton
    @Provides
    fun provideHomeApi(remoteDataSource: RemoteDataSource):
        HomeApi {
        return remoteDataSource.buildApi(
            "https://reqres.in/api/",
            HomeApi::class.java,
        )
    }
}
