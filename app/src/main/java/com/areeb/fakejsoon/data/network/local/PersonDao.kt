package com.areeb.fakejsoon.data.network.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(personDto: List<Person>)

    @Query("SELECT * FROM person")
    fun getPersons(): List<Person>
}
