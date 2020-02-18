package com.sample.gitrepos.repositories

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.FetchRepoService
import org.koin.core.inject

class ReposListRepositoryImpl : BaseRepository(), ReposListRepository{


    val fetchRepoWebservice: FetchRepoService by inject()
    override suspend fun getGitRepositories(): List<GitReposModel> {
        return  safeApiCall(call = {fetchRepoWebservice.fetchRepositoriesFromURL().await()}, errorMessage = "Error showed up" ) ?: mutableListOf()
    }

}