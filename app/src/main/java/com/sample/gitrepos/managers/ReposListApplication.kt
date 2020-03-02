package com.sample.gitrepos.managers

import androidx.multidex.MultiDexApplication
import com.sample.gitrepos.dimodules.*
import com.sample.gitrepos.utility.ConnectionUtility
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


class ReposListApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ReposListApplication)
            modules(listOf(viewModelModule, usecaseModule, repositoryModule, networkModule, gitReposListModule, dbModule))
        }


        ConnectionUtility.init(this)
    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}