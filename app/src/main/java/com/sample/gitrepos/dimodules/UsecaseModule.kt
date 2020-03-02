package com.sample.gitrepos.dimodules

import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import org.koin.dsl.module

val usecaseModule = module {
    factory { ReposListUseCaseImpl(get()) }
}