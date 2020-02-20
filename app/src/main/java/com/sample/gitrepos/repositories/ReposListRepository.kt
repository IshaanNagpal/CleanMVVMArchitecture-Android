package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposListModel
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource

interface ReposListRepository{
    suspend fun  getGitRepositories() : Resource<MutableList<GitReposModel>>
}