package com.example.fragmenttask.database

class NotifRepository(private val notifDao: NotiDao){
    val allNotifs:List<Notification> = notifDao.getAllNoti()

    suspend fun insert(notif: Notification){
        notifDao.insertNoti(notif)
    }
}