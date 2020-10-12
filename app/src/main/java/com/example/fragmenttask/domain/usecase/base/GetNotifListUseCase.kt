package com.example.fragmenttask.domain.usecase.base

import com.example.fragmenttask.database.NotifRepository
import com.example.fragmenttask.database.Notification

class GetNotifListUseCase (
    private var notifRepository: NotifRepository
   )  {
     fun run(params: Int): List<Notification> {
        return notifRepository.allNotifs
    }

}