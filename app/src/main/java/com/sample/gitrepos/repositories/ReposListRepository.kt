package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.network.Resource

interface ReposListRepository{
    suspend fun  getGitRepositories(forceFetch: Boolean): Resource<MutableList<GitReposModel>>
    suspend fun getDeltaInReposRefreshTime(): Long
}