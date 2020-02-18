package com.sample.gitrepos.network

import com.sample.gitrepos.models.GitReposListModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FetchRepoWebservice {

    @GET("repositories")
    fun fetchRepositoriesFromURL(): Deferred<Response<GitReposListModel>>

}