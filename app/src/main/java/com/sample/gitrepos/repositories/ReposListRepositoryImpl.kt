package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposListModel
import com.sample.gitrepos.network.FetchRepoWebservice

class ReposListRepositoryImpl(private val fetchRepoWebservice: FetchRepoWebservice) : BaseRepository(), ReposListRepository{

    override suspend fun getGitRepositories(): GitReposListModel {
        return  safeApiCall(call = {fetchRepoWebservice.fetchRepositoriesFromURL().await()}, errorMessage = "Error showed up" ) ?: GitReposListModel(
            mutableListOf())
    }

}