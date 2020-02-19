package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import org.koin.core.KoinComponent
import org.koin.core.inject

class ReposListUsecase: KoinComponent {

    private val repository: ReposListRepositoryImpl by inject()
    suspend fun askGitRepositoriesData(): MutableList<GitReposModel> {
        return repository.getGitRepositories()
    }

}