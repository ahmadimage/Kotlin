package com.example.fragmenttask.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noti_table")
data class Notification(val title: String, val time: Long){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}