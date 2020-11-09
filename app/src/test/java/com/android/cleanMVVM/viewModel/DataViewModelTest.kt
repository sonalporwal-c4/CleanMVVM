package com.android.cleanMVVM.viewModel

import com.android.cleanMVVM.data.entities.UserData
import com.android.cleanMVVM.data.repository.UserDataRepository
import com.android.cleanMVVM.presentation.ui.list.DataViewModel
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import org.junit.Test


class DataViewModelTest {
    
    @Test
    fun getData() {
        val userData = listOf(UserData(1, 1, "qui est esse", "eum et est occaecati"))
        val userDataRepository = mock<UserDataRepository>()
        val viewModel = DataViewModel(userDataRepository)
        viewModel.data = userDataRepository.getData()
        assertEquals(viewModel.data, listOf(userData))
    }
}