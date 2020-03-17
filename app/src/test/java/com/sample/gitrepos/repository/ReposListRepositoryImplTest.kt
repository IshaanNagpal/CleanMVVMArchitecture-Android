package com.sample.gitrepos.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.persistence.DaoHandlerImpl
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.utility.AppUtility
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock

class ReposListRepositoryImplTest : CoroutineTest() {


    private val reposListRepositoryImpl: ReposListRepositoryImpl by inject()
    private val daoHandlerImpl: DaoHandlerImpl = declareMock { }
    private val appUtility: AppUtility = declareMock {  }

    @Test
    fun `test for repository when cache is not available, forceFetch is false`() {

        runBlocking {
            //Given
            given(appUtility.isInternetAvailable()).willReturn(true)
            given(appUtility.getCurrentTime()).willReturn(3456789)
            given(daoHandlerImpl.getTimeValue()).willReturn(4567890)
            given(daoHandlerImpl.isReposDBEmpty()).willReturn(true)
            //When
            val resource = reposListRepositoryImpl.getGitRepositories(false)
            //Then
            verify(reposListRepositoryImpl).getGitRepositories(forceFetch = false)
            Assert.assertTrue(resource.status == Resource.Status.ERROR)
        }
    }

}