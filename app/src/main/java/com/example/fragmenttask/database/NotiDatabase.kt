package com.example.fragmenttask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Notification::class], version = 1, exportSchema = false)
abstract class NotiDatabase : RoomDatabase() {
    abstract fun notiDao(): NotiDao

    companion object {
        @Volatile
        private var INSTANCE: NotiDatabase? = null

        fun getDatabase(context: Context): NotiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotiDatabase::class.java,
                    "noti_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance

                return instance
            }

        }
    }
}