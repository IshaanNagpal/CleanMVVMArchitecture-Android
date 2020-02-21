package com.sample.gitrepos.repositories


import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.network.FetchRepoAPIService
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.persistence.ReposDao
import com.sample.gitrepos.persistence.TimestampDao
import org.koin.core.inject

class ReposListRepositoryImpl(private val reposDao: ReposDao, private val timestampDao: TimestampDao
) : BaseRepository(), ReposListRepository {

    private val fetchRepoWeatherWebservice: FetchRepoAPIService by inject()
    override suspend fun getGitRepositories(): Resource<MutableList<GitReposModel>> {

        return if (reposDao.getAllCachedRepos().isNullOrEmpty()) {
            val resource = safeApiCall(call = {
                fetchRepoWeatherWebservice.fetchRepositoriesFromURL().await()
            })
            resource.data?.let { reposDao.insertRepositories(it) }
            timestampDao.insertTimeStamp(Timestamp( System.currentTimeMillis()))
            resource
        } else {
            Resource.success(reposDao.getAllCachedRepos())
        }
    }

}