package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.utility.ListItemModel

interface ResposListUseCase {
    suspend fun getDataFromRepository(isForceFetch: Boolean = false): Resource<MutableList<GitReposModel>>
    suspend fun mapDataToViewItems(data: MutableList<GitReposModel>?): List<ListItemModel>
}