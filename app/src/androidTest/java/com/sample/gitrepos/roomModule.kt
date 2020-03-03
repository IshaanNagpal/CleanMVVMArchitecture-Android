package com.sample.gitrepos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.gitrepos.managers.ReposListApplication
import com.sample.gitrepos.persistence.AppDatabase
import org.koin.dsl.module

val roomTestModule = module {
    // In-Memory database config
    Room.inMemoryDatabaseBuilder( ApplicationProvider.getApplicationContext<ReposListApplication>(), AppDatabase::class.java)
        .allowMainThreadQueries()
        .build()
}