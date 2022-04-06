package com.example.minilydia.data.di

import com.example.minilydia.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { UsersViewModel(get()) }
}