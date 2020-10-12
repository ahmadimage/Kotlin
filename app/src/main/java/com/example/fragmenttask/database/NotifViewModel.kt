package com.example.fragmenttask.database

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.fragmenttask.base.BaseViewModel
import com.example.fragmenttask.domain.usecase.base.GetNotifListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotifViewModel(application: Application) : BaseViewModel() {

    private val repository: NotifRepository
    private lateinit var getNotifListUseCase: GetNotifListUseCase
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotifs: List<Notification>

    init {
        val notifsDao = NotiDatabase.getDatabase(application).notiDao()
        repository = NotifRepository(notifsDao)
        //allNotifs = repository.allNotifs
        getNotifListUseCase = GetNotifListUseCase(repository)
        allNotifs = getNotifListUseCase.run(7)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(notif: Notification) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notif)
    }

    fun getAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.allNotifs
    }


}