package com.sample.gitrepos.dimodules

import androidx.lifecycle.SavedStateHandle
import com.sample.gitrepos.viewmodels.ReposListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ReposListViewModelImpl(get(), get(), get()) }
    factory { SavedStateHandle() }
}