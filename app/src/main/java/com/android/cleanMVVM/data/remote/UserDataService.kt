package com.android.cleanMVVM.data.remote

import com.android.cleanMVVM.data.entities.UserData
import retrofit2.Response
import retrofit2.http.GET

interface UserDataService {
    @GET("https://jsonplaceholder.typicode.com/posts/")
    suspend fun getData() : Response<List<UserData>>
}