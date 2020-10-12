package com.example.fragmenttask.domain.usecase.base

interface UseCaseResponse<T> {
    fun onResponse(outcome: Outcome<T>)
}