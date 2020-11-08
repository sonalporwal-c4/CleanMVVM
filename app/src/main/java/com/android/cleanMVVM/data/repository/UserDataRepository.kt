package com.android.cleanMVVM.data.repository

import com.android.cleanMVVM.data.local.UserDataDao
import com.android.cleanMVVM.domain.interactors.RemoteDataSource
import com.android.cleanMVVM.utils.performGetCall
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: UserDataDao
) {

    fun getData() = performGetCall(
        databaseQuery = { localDataSource.getData() },
        networkCall = { remoteDataSource.getData() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}