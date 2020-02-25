package com.sample.gitrepos.repositories

import androidx.lifecycle.MutableLiveData
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource

interface ReposListRepository{
    suspend fun  getGitRepositories(forceFetch: Boolean)
    fun getReposResponseLiveData(): MutableLiveData<Resource<MutableList<GitReposModel>>>
}