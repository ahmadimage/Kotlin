package com.example.fragmenttask.domain.usecase

import com.example.fragmenttask.data.models.RetroResponse
import com.example.fragmenttask.domain.repository.ListRepository
import com.example.fragmenttask.domain.usecase.base.BaseUseCase

class GetListUseCase(
    private var listRepository: ListRepository
) : BaseUseCase<RetroResponse, Int>() {

    override suspend fun run(params: Int): RetroResponse {
        return listRepository.getNewsListing()
    }
}