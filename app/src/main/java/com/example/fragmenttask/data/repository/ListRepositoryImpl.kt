package com.example.fragmenttask.data.repository

import com.example.fragmenttask.data.models.RetroResponse
import com.example.fragmenttask.data.source.remote.ApiInterface
import com.example.fragmenttask.domain.repository.ListRepository

class ListRepositoryImpl(private var apiInterface: ApiInterface) : ListRepository {
    override suspend fun getNewsListing(): RetroResponse {
        return apiInterface.getCurrentRetroData()
    }
}