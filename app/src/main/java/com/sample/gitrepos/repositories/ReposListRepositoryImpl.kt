package com.sample.gitrepos.repositories


import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.FetchRepoAPIService
import com.sample.gitrepos.network.Resource
import org.koin.core.inject

class ReposListRepositoryImpl : BaseRepository(), ReposListRepository {

    private val fetchRepoWeatherWebservice: FetchRepoAPIService by inject()
    override suspend fun getGitRepositories(): Resource<MutableList<GitReposModel>> {
        return safeApiCall(call = { fetchRepoWeatherWebservice.fetchRepositoriesFromURL().await()})
    }

}