package com.areeb.fakejsoon.data.network.local

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.areeb.fakejsoon.utils.Constants.PERSON_TABLE

@androidx.room.Entity(tableName = PERSON_TABLE)
data class PersonEntity(

    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("avatar")
    val avatar: String,
    @ColumnInfo("first_name")
    val first_name: String,
    @ColumnInfo("last_name")
    val last_name: String,
)
