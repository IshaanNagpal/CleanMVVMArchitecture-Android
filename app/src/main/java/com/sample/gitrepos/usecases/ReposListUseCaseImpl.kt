package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.repositories.ReposListRepositoryImpl

class ReposListUseCaseImpl(private val repository: ReposListRepositoryImpl) : ResposListUseCase {

    override suspend fun getDataFromRepository(isForceFetch: Boolean): Resource<MutableList<GitReposModel>> {
        return repository.getGitRepositories(isForceFetch)
    }

}