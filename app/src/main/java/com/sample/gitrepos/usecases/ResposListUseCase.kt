package com.sample.gitrepos.usecases

import androidx.lifecycle.MutableLiveData
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource

interface ResposListUseCase {
    fun subscribeForReposData(): MutableLiveData<Resource<MutableList<GitReposModel>>>
    suspend fun getDataFromRepository(isForceFetch: Boolean = false)
}