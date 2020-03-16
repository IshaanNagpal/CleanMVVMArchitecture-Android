package com.sample.gitrepos.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.models.Timestamp
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.persistence.DaoHandlerImpl
import com.sample.gitrepos.persistence.ReposDao
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.utility.ConnectionUtility
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito.`when`

class ReposListRepositoryImplTest : CoroutineTest() {


    private val reposListRepositoryImpl: ReposListRepositoryImpl by inject()
    private val daoHandlerImpl: DaoHandlerImpl = declareMock { }
    private val connectionUtility: ConnectionUtility = declareMock {  }

    @Test
    fun `test for repository when cache is not available, forceFetch is false`() {

        runBlocking {
            //Given
            whenever(connectionUtility.isInternetAvailable()).then { true }
            given(daoHandlerImpl.isReposDBEmpty()).willReturn(true)
//            given(daoHandlerImpl.getTimeStampFromDB().timesTamp).willReturn(System.currentTimeMillis())
            //When
            val resource = reposListRepositoryImpl.getGitRepositories(false)
            //Then
            Assert.assertTrue(resource.status == Resource.Status.ERROR)
        }

    }

}