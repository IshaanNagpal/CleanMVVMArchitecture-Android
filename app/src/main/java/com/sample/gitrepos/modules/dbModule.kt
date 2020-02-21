package com.sample.gitrepos.modules

import androidx.room.Room
import com.sample.gitrepos.persistence.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "git_repos_db").build() }
    single { get<AppDatabase>().reposDao() }
    single { get<AppDatabase>().timeStampDao() }
}