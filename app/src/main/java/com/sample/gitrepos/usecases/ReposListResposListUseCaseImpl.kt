package com.sample.gitrepos.usecases


import androidx.lifecycle.MutableLiveData
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.repositories.ReposListRepositoryImpl

class ReposListResposListUseCaseImpl(private val repository: ReposListRepositoryImpl) : ResposListUseCase {

    override fun subscribeForReposData(): MutableLiveData<Resource<MutableList<GitReposModel>>> {
       return repository.getReposResponseLiveData()
    }

    override suspend fun getDataFromRepository() {
        return repository.getGitRepositories()
    }

}