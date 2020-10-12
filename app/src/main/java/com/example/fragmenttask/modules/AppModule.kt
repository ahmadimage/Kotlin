package com.example.fragmenttask.modules

import com.example.fragmenttask.data.repository.ListRepositoryImpl
import com.example.fragmenttask.data.source.remote.ApiInterface
import com.example.fragmenttask.database.NotiDao
import com.example.fragmenttask.database.NotifRepository
import com.example.fragmenttask.database.NotifViewModel
import com.example.fragmenttask.domain.repository.ListRepository
import com.example.fragmenttask.domain.usecase.GetListUseCase
import com.example.fragmenttask.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var RepositoryModule = module {
    single { createNewsRepo(get()) }
}

var NewsModules = module {
    single { createGetNewsListUseCase(get()) }
    viewModel { ListViewModel(get()) }
}

var NotifModule = module {
    single { createNotifRepo(get()) }
    viewModel { NotifViewModel(get()) }
}

fun createNotifRepo(notiDao: NotiDao): NotifRepository{
    return NotifRepository(notiDao)
}

fun createGetNewsListUseCase(listRepository: ListRepository): GetListUseCase {
    return GetListUseCase(listRepository)
}

fun createNewsRepo(apiInterface: ApiInterface): ListRepository {
    return ListRepositoryImpl(apiInterface)
}
