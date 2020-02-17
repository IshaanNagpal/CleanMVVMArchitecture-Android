package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposListModel

interface ReposListRepository{
    suspend fun getGitRepositories() : GitReposListModel
}