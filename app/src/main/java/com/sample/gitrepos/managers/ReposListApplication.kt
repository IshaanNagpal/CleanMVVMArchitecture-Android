package com.sample.gitrepos.managers

import androidx.multidex.MultiDexApplication
import com.sample.gitrepos.modules.networkModule
import com.sample.gitrepos.modules.repositoryModule
import com.sample.gitrepos.modules.usecaseModule
import com.sample.gitrepos.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ReposListApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ReposListApplication)
            modules(listOf(viewModelModule, repositoryModule, usecaseModule, networkModule))
        }
    }

}