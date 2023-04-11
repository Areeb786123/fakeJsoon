package com.areeb.fakejsoon.di

import android.content.Context
import com.areeb.fakejsoon.data.RemoteDataSource
import com.areeb.fakejsoon.data.network.local.AppDataBase
import com.areeb.fakejsoon.data.network.local.PersonDao
import com.areeb.fakejsoon.data.network.remote.api.home.HomeApi
import com.areeb.fakejsoon.data.network.remote.api.home.PostApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providePostApi(remoteDataSource: RemoteDataSource): PostApi {
        return remoteDataSource.buildApi(
            "https://reqres.in/api/",
            PostApi::class.java,
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getDatabase(context)
    }

    @Provides
    fun providePersonDao(appDatabase: AppDataBase): PersonDao {
        return appDatabase.personDao()
    }
}
