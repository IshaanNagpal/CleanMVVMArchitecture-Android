package com.sample.gitrepos.dimodules

import com.sample.gitrepos.utility.AppUtility
import org.koin.dsl.module

val utilityModule = module {
    single { AppUtility(get()) }
}