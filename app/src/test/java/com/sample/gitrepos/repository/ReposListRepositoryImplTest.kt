package com.sample.gitrepos.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.stub
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
    fun `Asked from repository when no internet, no cache & forceFetch is false, must return error`() {

        runBlocking {
            //Given
            given(appUtility.isInternetAvailable()).willReturn(false)
            given(appUtility.getCurrentTime()).willReturn(3456789)
            given(daoHandlerImpl.isReposDBEmpty()).willReturn(true)
            daoHandlerImpl.stub {
                onBlocking { getTimeValue() }.doReturn(4567890)
            }
            //When
            val resource = reposListRepositoryImpl.getGitRepositories(false)
            //Then
            Assert.assertTrue(resource.status == Resource.Status.ERROR)
        }
    }


    @Test
    fun `Asked from repository when no internet, cache available & forceFetch is false, must return success & do not update DB`() {

        runBlocking {
            //Given
            val currentTime = System.currentTimeMillis()
            given(appUtility.isInternetAvailable()).willReturn(false)
            given(appUtility.getCurrentTime()).willReturn(3456789)
            given(daoHandlerImpl.isReposDBEmpty()).willReturn(false)
            daoHandlerImpl.stub {
                onBlocking { getTimeValue() }.doReturn(456788983)
            }
            //When
            val resource = reposListRepositoryImpl.getGitRepositories(false)
            //Then
            Assert.assertTrue(resource.status == Resource.Status.SUCCESS)
            Assert.assertNotEquals(currentTime, daoHandlerImpl.getTimeValue())
        }
    }

    @Test
    fun `Asked from repository when internet & cache available & forceFetch is false, must return success & update DB`() {

        runBlocking {
            //Given
            val currentTime = System.currentTimeMillis()
            given(appUtility.isInternetAvailable()).willReturn(true)
            given(appUtility.getCurrentTime()).willReturn(3456789)
            given(daoHandlerImpl.isReposDBEmpty()).willReturn(false)
            daoHandlerImpl.stub {
                onBlocking { getTimeValue() }.doReturn(currentTime)
            }
            //When
            val resource = reposListRepositoryImpl.getGitRepositories(true)
            //Then
            Assert.assertTrue(resource.status == Resource.Status.SUCCESS)
            Assert.assertEquals(currentTime, daoHandlerImpl.getTimeValue())
        }
    }

}