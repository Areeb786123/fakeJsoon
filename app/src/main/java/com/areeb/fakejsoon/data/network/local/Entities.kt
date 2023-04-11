package com.areeb.fakejsoon.data.network.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val tableId: Int?,
    @ColumnInfo(name = "avatar") val avatar: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val first_name: String,
    @ColumnInfo(name = "person_id") val id: Int,
    @ColumnInfo(name = "last_name") val last_name: String,

)

