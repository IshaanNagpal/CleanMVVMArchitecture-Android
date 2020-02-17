package com.sample.gitrepos.network

import com.sample.gitrepos.models.GitReposListModel
import retrofit2.http.GET

interface FetchRepoWebservice {

    @GET("repositories")
    suspend fun fetchRepositoriesFromURL(): GitReposListModel

}