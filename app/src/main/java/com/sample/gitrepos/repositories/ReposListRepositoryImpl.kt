package com.sample.gitrepos.repositories


import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.network.FetchRepoAPIService
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.persistence.ReposDao
import com.sample.gitrepos.persistence.TimestampDao
import com.sample.gitrepos.utility.TWO_HOURS_MILLIS
import org.koin.core.inject

class ReposListRepositoryImpl(private val reposDao: ReposDao, private val timestampDao: TimestampDao
) : BaseRepository(), ReposListRepository {

    private val fetchRepoWeatherWebservice: FetchRepoAPIService by inject()

    override suspend fun getGitRepositories(): Resource<MutableList<GitReposModel>> {
        return if (System.currentTimeMillis() - getLastSavedTimeStamp() >= TWO_HOURS_MILLIS || reposDao.getAllCachedRepos().isNullOrEmpty()) {
            val resource = safeApiCall(call = {
                fetchRepoWeatherWebservice.fetchRepositoriesFromURL().await()
            })
            resource.data?.let { reposDao.insertRepositories(it) }                              //Adding the repositories list data in repos table, ROOM
            timestampDao.insertTimeStamp(Timestamp(System.currentTimeMillis()))                 //Adding the current time in timestamp table, ROOM
            resource
        } else {
            Resource.success(reposDao.getAllCachedRepos())
        }
    }

    private suspend fun getLastSavedTimeStamp(): Long {
        val lastTime = timestampDao.getLastSavedTimeStamp()?.timesTamp ?: 0
        return lastTime
    }

}