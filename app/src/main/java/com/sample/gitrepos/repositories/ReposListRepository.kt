package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposModel

interface ReposListRepository{
    suspend fun getGitRepositories() : List<GitReposModel>
}