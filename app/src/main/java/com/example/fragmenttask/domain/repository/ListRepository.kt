package com.example.fragmenttask.domain.repository

import com.example.fragmenttask.data.models.RetroResponse

interface ListRepository {
    suspend fun getNewsListing(): RetroResponse
}