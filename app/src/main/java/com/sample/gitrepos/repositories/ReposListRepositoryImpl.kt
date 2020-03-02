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
        if(!ConnectionUtility.isInternetAvailable()) {
            return if(daoHandlerImpl.getReposDataFromDB().isNullOrEmpty()) {
                Resource.error(ResourceError())
            } else{
                Resource.success(daoHandlerImpl.getReposDataFromDB())
            }

        } else {
            return if (!forceFetch && !daoHandlerImpl.getReposDataFromDB().isNullOrEmpty() && System.currentTimeMillis() - getLastSavedTimeStamp() < TWO_HOURS_MILLIS) {
                Resource.success(daoHandlerImpl.getReposDataFromDB())
            } else {
                val resource = safeApiCall(call = { fetchRepoWeatherWebservice.fetchRepositoriesFromURL().await() })
                if(resource.status == Resource.Status.SUCCESS) {
                    updateDatabase(resource)
                }
                resource
            }
        }
    }

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