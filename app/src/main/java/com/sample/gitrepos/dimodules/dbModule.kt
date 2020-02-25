package com.sample.gitrepos.dimodules

import androidx.room.Room
import com.sample.gitrepos.persistence.AppDatabase
import com.sample.gitrepos.persistence.DaoHandlerImpl
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "git_repos_db").build() }
    single { get<AppDatabase>().reposDao() }
    single { get<AppDatabase>().timeStampDao() }
    single { DaoHandlerImpl(get(), get()) }
}