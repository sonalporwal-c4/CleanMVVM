package com.android.cleanMVVM.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.cleanMVVM.data.entities.UserData

@Dao
interface UserDataDao {

    @Query("SELECT * FROM data")
    fun getData() : LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userData: List<UserData>)

}