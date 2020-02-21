package com.sample.gitrepos.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.gitrepos.models.GitReposModel

@Dao
interface ReposDao {

    @Query("SELECT * from `repositories`")
    suspend fun getAllCachedRepos(): MutableList<GitReposModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(reposLIst: MutableList<GitReposModel>)

    @Query("DELETE from `repositories`")
    suspend fun deleteAllRepositories()
}