package com.sample.gitrepos.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp

@Database(entities = [GitReposModel::class, Timestamp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reposDao(): ReposDao
    abstract fun timeStampDao(): TimestampDao
}