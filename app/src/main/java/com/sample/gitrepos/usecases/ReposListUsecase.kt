package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposListModel
import com.sample.gitrepos.repositories.ReposListRepository

class ReposListUsecase(private val repository: ReposListRepository) {

    suspend fun askGitRepositoriesData(): GitReposListModel {
        return repository.getGitRepositories()
    }

}