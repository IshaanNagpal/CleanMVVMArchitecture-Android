package com.sample.gitrepos.usecases

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.utility.*
import com.sample.gitrepos.views.ReposItemView

class ReposListUseCaseImpl(private val repository: ReposListRepositoryImpl, private val reposListStringHelper: ReposListStringHelper) : ResposListUseCase {

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

    override suspend fun getLastRefreshedString(): String {

        return when (repository.getDeltaInReposRefreshTime()) {
            in (ONE_MIN_MILLIS + 1) until THIRTY_MINS_MILLIS -> {
                reposListStringHelper.getStringLabelZeroToThirty()
            }
            in (THIRTY_MINS_MILLIS + 1) until SIXTY_MINS_MILLIS -> {
                reposListStringHelper.getStringThirtyToSixty()
            }
            in (SIXTY_MINS_MILLIS + 1) until TWO_HOURS_MILLIS -> {
                reposListStringHelper.getStringAnHour()
            }
            else -> reposListStringHelper.getStringThirtyToSixty()
        }
    }



}