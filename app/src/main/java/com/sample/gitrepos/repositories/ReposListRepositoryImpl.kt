package com.sample.gitrepos.repositories

import com.sample.gitrepos.network.FetchRepoWebservice

class ReposListRepositoryImpl(private val fetchRepoWebservice: FetchRepoWebservice) : ReposListRepository{

    override suspend fun getGitRepositories()  = fetchRepoWebservice.fetchRepositoriesFromURL()

}