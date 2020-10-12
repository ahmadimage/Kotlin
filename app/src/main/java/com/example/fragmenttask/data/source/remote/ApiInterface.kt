package com.example.fragmenttask.data.source.remote

import com.example.fragmenttask.data.models.RetroResponse
import retrofit2.http.GET


interface ApiInterface {
    @GET("v3/fbd3da19-9ada-4daa-925d-2f177fc5dd86")
    suspend fun  getCurrentRetroData(): RetroResponse
}