package com.sample.gitrepos.modules

import com.sample.gitrepos.usecases.ReposListUsecase
import org.koin.dsl.module

val usecaseModule = module {
    factory { ReposListUsecase(get()) }
}