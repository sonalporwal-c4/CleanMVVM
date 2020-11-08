package com.android.cleanMVVM.domain.interactors

import com.android.cleanMVVM.data.remote.UserDataService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val userDataService: UserDataService
): BaseDataSource() {

    suspend fun getData() = getResult { userDataService.getData() }
}