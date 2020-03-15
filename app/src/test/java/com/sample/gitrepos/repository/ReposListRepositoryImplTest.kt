package com.sample.gitrepos.repository

import com.nhaarman.mockito_kotlin.whenever
import com.sample.gitrepos.BaseTest
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.persistence.DaoHandlerImpl
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.utility.ConnectionUtility
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

class ReposListRepositoryImplTest : BaseTest() {


    private val reposListRepositoryImpl: ReposListRepositoryImpl by inject()
    private val daoHandlerImpl: DaoHandlerImpl = declareMock { }

    @ExperimentalCoroutinesApi
    @Test
    fun `test for repository when cache is not available, forceFetch is false`() {

        ConnectionUtility.init(context)
        runBlocking {
            //Given
//            whenever(ConnectionUtility.isInternetAvailable()).then { true }
            whenever(daoHandlerImpl.isReposDBEmpty()).then { true }
//            whenever(daoHandlerImpl.getTimeStampFromDB()).then { System.currentTimeMillis() }

            //When
//            val successResource = reposListUseCaseImpl.getGitRepositories(true)
            val resource = reposListRepositoryImpl.getGitRepositories(false)

            //Then
            Assert.assertTrue(resource.status == Resource.Status.ERROR)
        }

    }

}