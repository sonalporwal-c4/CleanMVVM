package com.android.cleanMVVM.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.android.cleanMVVM.utils.State.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetCall(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> State<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<State<T>> =
    liveData(Dispatchers.IO) {
        emit(State.loading())
        val source = databaseQuery.invoke().map { State.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(State.error(responseStatus.message!!))
            emitSource(source)
        }
    }