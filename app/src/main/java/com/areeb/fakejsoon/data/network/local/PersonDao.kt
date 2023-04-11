package com.areeb.fakejsoon.data.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.areeb.fakejsoon.utils.Constants.PERSON_TABLE

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(personEntity: List<PersonEntity>)

    @Query("SELECT * FROM $PERSON_TABLE")
    suspend fun getAllUsers(): List<PersonEntity>
}
