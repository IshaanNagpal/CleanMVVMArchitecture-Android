package com.sample.gitrepos.usecases

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock


class ReposListUseCaseImplTest : CoroutineTest() {

    private val reposListUseCaseImpl: ReposListUseCaseImpl by inject()
    private val errorResource = Resource.error<ResourceError>(ResourceError())
    private val successResource = Resource.success(mutableListOf<GitReposModel>())
    private val gitReposRepository: ReposListRepositoryImpl = declareMock {  }

    @Test
    fun `verify for usecase fetching data from repository will return in success or error`() {
        runBlocking {
            //Given
            given(gitReposRepository.getGitRepositories(forceFetch = true)).will{ successResource }
            given(gitReposRepository.getGitRepositories(forceFetch = false)).will{ errorResource }
            //When
            val successResource = reposListUseCaseImpl.getDataFromRepository(true)
            val errorResource = reposListUseCaseImpl.getDataFromRepository(false)
            //Then
            verify(gitReposRepository).getGitRepositories(true)    //Test that method calling has happened
            Assert.assertTrue(successResource.status == Resource.Status.SUCCESS)
            Assert.assertTrue(errorResource.status == Resource.Status.ERROR)
        }

    }

    @Test
    fun `verify the mapping of data to itemview returns list of item model when datalist is not null or empty`() {
        runBlocking {
            val gitReposModel = GitReposModel("author", "", forks = 1, language = "Kotlin", languageColor = "#ddd", name = "Demo", stars = 5, url = "")
            val gitReposList = mutableListOf(gitReposModel)
            val itemModelList = reposListUseCaseImpl.mapDataToViewItems(gitReposList)
            Assert.assertFalse(itemModelList.isNullOrEmpty())
            Assert.assertTrue(itemModelList[0] is ReposItemView)
            Assert.assertTrue((itemModelList[0] as ReposItemView).author == gitReposModel.author)
        }
    }

    @Test
    fun `verify the mapping of data to itemview returns empty list when datalist is null`() {
        runBlocking {
            val itemModelList = reposListUseCaseImpl.mapDataToViewItems(null)
            Assert.assertTrue(itemModelList.isEmpty())
        }
    }


    @Test
    fun `verify the mapping of data to itemview return empty list when datalist is empty`() {
        runBlocking {
            val itemModelList = reposListUseCaseImpl.mapDataToViewItems(mutableListOf())
            Assert.assertTrue(itemModelList.isEmpty())
        }
    }

}