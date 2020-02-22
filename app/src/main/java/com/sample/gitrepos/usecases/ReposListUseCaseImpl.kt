package com.sample.gitrepos.usecases


import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import org.koin.core.inject

class ReposListUseCaseImpl : UseCase<MutableList<GitReposModel>> {
    private val repository: ReposListRepositoryImpl by inject()

    override suspend fun getDataFromRepository(): Resource<MutableList<GitReposModel>> {
        return repository.getGitRepositories()
    }

}