package com.sample.gitrepos.usecases

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock


class ReposListUseCaseImplTest : CoroutineTest() {


    private val reposListUseCaseImpl: ReposListRepositoryImpl by inject()
    private val errorResource = Resource.error<ResourceError>(ResourceError())
    private val successResource = Resource.success(mutableListOf<GitReposModel>())
    private val gitReposRepository: ReposListRepositoryImpl = declareMock {  }


    @Test
    fun `verify for usecase fetching data from repository will return success`() {

        runBlocking {

            //Given
            given(gitReposRepository.getGitRepositories(forceFetch = true)).will{ successResource }
            given(gitReposRepository.getGitRepositories(forceFetch = false)).will{ errorResource }
            //When
            val successResource = reposListUseCaseImpl.getGitRepositories(true)
            val errorResource = reposListUseCaseImpl.getGitRepositories(false)
            //Then
            verify(gitReposRepository).getGitRepositories(true)
            Assert.assertTrue(successResource.status == Resource.Status.SUCCESS)
            Assert.assertTrue(errorResource.status == Resource.Status.ERROR)
        }

    }

}