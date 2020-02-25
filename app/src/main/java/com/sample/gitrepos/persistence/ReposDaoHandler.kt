package com.sample.gitrepos.persistence

import com.sample.gitrepos.models.GitReposModel

interface ReposDaoHandler {
    suspend fun getReposDataFromDB(): MutableList<GitReposModel>
    suspend fun isReposDBEmpty(): Boolean
    suspend fun addReposDataIntoDB(gitReposModelList: MutableList<GitReposModel>)
}