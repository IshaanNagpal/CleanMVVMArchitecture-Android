package com.sample.gitrepos

import androidx.test.core.app.ApplicationProvider
import com.sample.gitrepos.dimodules.*
import com.sample.gitrepos.managers.ReposListApplication
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

open class AndroidBaseTest: AutoCloseKoinTest() {
    val reposApplicationContext = ApplicationProvider.getApplicationContext<ReposListApplication>()


    @Before
    fun beforeEach() {

        if(GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(reposApplicationContext)
                modules(listOf(viewModelModule, usecaseModule, repositoryModule, networkModule, gitReposListModule, mockDbModule))
            }
        }
    }

    @After
    fun tearDown() {
        autoClose()
    }
}