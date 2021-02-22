package com.example.chamaproject.di

import com.example.chamaproject.data.api.RetrofitClient
import com.example.chamaproject.data.repository.MapsRepository
import com.example.chamaproject.ui.viewmodel.MapsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { MapsRepository() }
    viewModel { MapsViewModel(get()) }
    single { RetrofitClient }
}
