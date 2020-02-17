package com.sample.gitrepos.usecases

import com.sample.gitrepos.repositories.ReposListRepository

class ReposListUsecase(private val repository: ReposListRepository) {

    fun askGitRepositoriesData() {
        repository.getGitRepositories()
    }

}