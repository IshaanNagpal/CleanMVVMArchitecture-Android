package com.sample.gitrepos.modules

import com.sample.gitrepos.viewmodels.ReposListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ReposListViewModel(get()) }
}