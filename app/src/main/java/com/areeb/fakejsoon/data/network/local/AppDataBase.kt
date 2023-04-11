package com.areeb.fakejsoon.data.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class AppDataBase : RoomDatabase(), DataBaseOperations, SafeDaoCall {
    abstract fun personDao(): PersonDao

    override suspend fun insertAllPersons(listOfPerson: List<Person>) {
        safeDaoApiCall { personDao().insertPerson(listOfPerson) }
    }

    override suspend fun getAllPersons(): DbResult<List<Person>> {
        return safeDaoApiCall { personDao().getPersons() }
    }

}
