package com.sample.gitrepos.network

import com.sample.gitrepos.models.GitReposModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FetchRepoAPIService {
    @GET("repositories")
    fun fetchRepositoriesFromURL(): Deferred<Response<MutableList<GitReposModel>>>
}