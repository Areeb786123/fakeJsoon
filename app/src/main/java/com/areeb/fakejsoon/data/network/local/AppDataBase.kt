package com.areeb.fakejsoon.data.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.areeb.fakejsoon.utils.Constants.PERSON_DATABASE

@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    PERSON_DATABASE,
                ).build()
            }
            return INSTANCE!!
        }
    }
}
