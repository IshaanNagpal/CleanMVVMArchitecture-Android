package com.sample.gitrepos.repositories


import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.network.FetchRepoAPIService
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.persistence.DaoHandlerImpl
import com.sample.gitrepos.utility.ConnectionUtility
import com.sample.gitrepos.utility.TWO_HOURS_MILLIS

class ReposListRepositoryImpl(private val fetchRepoWeatherWebservice: FetchRepoAPIService, private val daoHandlerImpl: DaoHandlerImpl) : BaseRepository(),
    ReposListRepository {


    override suspend fun getGitRepositories(forceFetch: Boolean): Resource<MutableList<GitReposModel>> {
        return if (!isCacheStale() && !forceFetch) {                                           //First check for cache is stale or force fetch is required
            if (daoHandlerImpl.isReposDBEmpty()) Resource.error(ResourceError())
            else Resource.success(daoHandlerImpl.getReposDataFromDB())
        } else {
            if (ConnectionUtility.isInternetAvailable()) {
                val resource = safeApiCall(call = {
                    fetchRepoWeatherWebservice.fetchRepositoriesFromURL().await()
                })
                if (resource.status == Resource.Status.SUCCESS) {
                    updateDatabase(resource)
                }
                resource
            } else {
                return if (daoHandlerImpl.isReposDBEmpty() || forceFetch) {
                    Resource.error(ResourceError())
                } else {
                    Resource.success(daoHandlerImpl.getReposDataFromDB())
                }
            }
        }
    }


    private suspend fun isCacheStale(): Boolean =
        System.currentTimeMillis() - getLastSavedTimeStamp() >= TWO_HOURS_MILLIS

    private suspend fun updateDatabase(resource: Resource<MutableList<GitReposModel>>) {
        resource.data?.let {
            daoHandlerImpl.addReposDataIntoDB(it)
            daoHandlerImpl.addTimeStampInDB(Timestamp(System.currentTimeMillis()))
        }
    }

    private suspend fun getLastSavedTimeStamp(): Long {
        return daoHandlerImpl.getTimeStampFromDB().timesTamp
    }

}