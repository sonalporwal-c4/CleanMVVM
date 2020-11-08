package com.android.cleanMVVM.presentation.ui.di

import android.content.Context
import com.android.cleanMVVM.data.local.AppDatabase
import com.android.cleanMVVM.data.local.UserDataDao
import com.android.cleanMVVM.data.remote.UserDataService
import com.android.cleanMVVM.data.repository.UserDataRepository
import com.android.cleanMVVM.domain.interactors.RemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/posts/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserDataService(retrofit: Retrofit): UserDataService = retrofit.create(
        UserDataService::class.java)

    @Singleton
    @Provides
    fun provideUserDataRemoteDataSource(userDataService: UserDataService) = RemoteDataSource(userDataService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDataDao(db: AppDatabase) = db.userDataDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: UserDataDao
    ) =
        UserDataRepository(remoteDataSource, localDataSource)
}