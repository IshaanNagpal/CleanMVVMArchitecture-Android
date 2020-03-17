package com.sample.gitrepos

import android.app.Application
import com.sample.gitrepos.dimodules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinTestApplication)
            modules(listOf(viewModelModule, usecaseModule, repositoryModule, networkModule, gitReposListModule, dbModule, utilityModule))
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}