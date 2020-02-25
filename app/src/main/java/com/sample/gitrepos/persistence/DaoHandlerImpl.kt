package com.sample.gitrepos.persistence

import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.models.Timestamp

class DaoHandlerImpl(private val reposDao: ReposDao, private val timestampDao: TimestampDao) : ReposDaoHandler, TimestampDaoHandler {
    override suspend fun isReposDBEmpty(): Boolean {
        return reposDao.getAllCachedRepos().isNullOrEmpty()
    }

    override suspend fun getReposDataFromDB(): MutableList<GitReposModel> {
        return reposDao.getAllCachedRepos()
    }

    override suspend fun addReposDataIntoDB(gitReposModelList: MutableList<GitReposModel>) {
        reposDao.insertRepositories(gitReposModelList)
    }

    override suspend fun getTimeStampFromDB(): Timestamp  {
        return timestampDao.getLastSavedTimeStamp()
    }

    override suspend fun addTimeStampInDB(timestamp: Timestamp) {
        timestampDao.insertTimeStamp(timestamp)
    }
}