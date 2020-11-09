package com.android.cleanMVVM.repository

import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.data.local.UserDataDao
import com.android.cleanMVVM.data.repository.UserDataRepository
import com.android.cleanMVVM.domain.interactors.RemoteDataSource
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.CompletableFuture


class UserDataRepositoryTest {

    @Test
    fun getData() {
        val userData = listOf(UserData(1, 1, "qui est esse", "eum et est occaecati"))
        val remoteDataSource = mock<RemoteDataSource>()
        val userDao = mock<UserDataDao>()
        val repo = UserDataRepository(remoteDataSource, userDao)
        val future = CompletableFuture<List<UserData>>()
        repo.getData()
        assertEquals(future.get(), listOf(userData))
    }
}