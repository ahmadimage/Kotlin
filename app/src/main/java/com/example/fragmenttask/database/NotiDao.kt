package com.example.fragmenttask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNoti(noti: Notification)

    @Query("SELECT * FROM noti_table ORDER BY id ASC")
    fun getAllNoti(): List<Notification>

    @Query("DELETE FROM noti_table")
    fun deleteAll()

}