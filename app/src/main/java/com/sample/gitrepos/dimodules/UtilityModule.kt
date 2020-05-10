package com.sample.gitrepos.dimodules

import com.sample.gitrepos.utility.AppUtility
import com.sample.gitrepos.utility.ReposListStringHelper
import org.koin.dsl.module

val utilityModule = module {
    single { AppUtility(get()) }
    factory { ReposListStringHelper(get()) }
}