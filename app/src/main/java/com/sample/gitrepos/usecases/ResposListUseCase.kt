package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource

interface ResposListUseCase {
    suspend fun getDataFromRepository(isForceFetch: Boolean = false): Resource<MutableList<GitReposModel>>
}