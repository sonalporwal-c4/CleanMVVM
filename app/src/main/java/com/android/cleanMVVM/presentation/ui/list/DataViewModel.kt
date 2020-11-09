package com.android.cleanMVVM.presentation.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.android.cleanMVVM.data.repository.UserDataRepository

class DataViewModel @ViewModelInject constructor(
    private val repository: UserDataRepository
) : ViewModel() {

    var data = repository.getData()

}
