package com.sample.gitrepos.dimodules

import com.sample.gitrepos.usecases.ReposListReposListUseCaseImpl
import org.koin.dsl.module

val usecaseModule = module {
    factory { ReposListReposListUseCaseImpl(get()) }
}