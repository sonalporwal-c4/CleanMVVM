package com.android.cleanMVVM.interactor

import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.data.remote.UserDataService
import com.android.cleanMVVM.domain.interactors.RemoteDataSource
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.CompletableFuture

class RemoteDataSourceTest {

    @Test
   suspend fun getData() {
        val userData = listOf(UserData(1,1,"qui est esse", "eum et est occaecati"))
        val userDataService = mock<UserDataService>()
        val repo = RemoteDataSource(userDataService)
        val future = CompletableFuture<List<UserData>>()
        repo.getData()
        assertEquals(future.get(), listOf(userData))
    }
}