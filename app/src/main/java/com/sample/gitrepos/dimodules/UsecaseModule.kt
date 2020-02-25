package com.sample.gitrepos.dimodules

import com.sample.gitrepos.usecases.ReposListResposListUseCaseImpl
import org.koin.dsl.module

val usecaseModule = module {
    factory { ReposListResposListUseCaseImpl(get()) }
}