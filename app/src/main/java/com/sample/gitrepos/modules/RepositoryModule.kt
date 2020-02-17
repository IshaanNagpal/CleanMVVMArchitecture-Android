package com.sample.gitrepos.modules

import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ReposListRepositoryImpl(get()) }
}