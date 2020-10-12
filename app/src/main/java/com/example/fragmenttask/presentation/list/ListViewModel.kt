package com.example.fragmenttask.presentation.list

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fragmenttask.base.BaseViewModel
import com.example.fragmenttask.data.models.Devices
import com.example.fragmenttask.data.models.RetroResponse
import com.example.fragmenttask.domain.usecase.GetListUseCase
import com.example.fragmenttask.domain.usecase.base.Outcome
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel(
    private var getListUseCase: GetListUseCase
) : BaseViewModel() {

    private lateinit var deviceLiveData: MutableLiveData<Outcome<RetroResponse>>
    private var resultLiveData: MediatorLiveData<List<Devices>> = MediatorLiveData()

    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun getListLiveData() = resultLiveData

    fun fetchList() {
        if (this::deviceLiveData.isInitialized)
            resultLiveData.removeSource(deviceLiveData)
        deviceLiveData = getListUseCase.start(
            true,
            viewModelScope, 7
        )
        resultLiveData.addSource(deviceLiveData) { outcome ->
            outcomeLiveData.value = outcome
            when (outcome) {
                is Outcome.Success -> outcome.data.devices.let {
                    resultLiveData.value = it
                }
            }
        }
    }

    fun getListOfDevices(callback: (List<Devices>) -> Unit) {
        GlobalScope.launch {
            val dataList = getListUseCase.run(4).devices
            mainThreadHandler.post { callback(dataList) }
        }
    }



}