package com.sample.gitrepos


import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(manifest=Config.NONE, application = KoinTestApplication::class)
@RunWith(RobolectricTestRunner::class)
open class BaseTest : AutoCloseKoinTest() {
    val context: KoinTestApplication = ApplicationProvider.getApplicationContext()

}