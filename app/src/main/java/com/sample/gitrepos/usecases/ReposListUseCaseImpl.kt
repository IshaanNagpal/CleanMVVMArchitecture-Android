package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposItemView

class ReposListUseCaseImpl(private val repository: ReposListRepositoryImpl) : ResposListUseCase {

    override suspend fun getDataFromRepository(isForceFetch: Boolean): Resource<MutableList<GitReposModel>> {
        return repository.getGitRepositories(isForceFetch)
    }

    override suspend fun mapDataToViewItems(data: MutableList<GitReposModel>?): List<ListItemModel> {
        val reposItemViewList = mutableListOf<ListItemModel>()
        data?.map { gitReposModel ->
            reposItemViewList.add(ReposItemView(gitReposModel))
        }
        return reposItemViewList
    }

}