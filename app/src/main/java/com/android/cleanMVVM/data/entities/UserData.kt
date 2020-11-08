package com.android.cleanMVVM.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Data")
data class UserData(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)