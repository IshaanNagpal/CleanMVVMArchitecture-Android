package com.sample.gitrepos.viewmodels

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.repositories.ReposListRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ReposListViewModelImplTest {

    private lateinit var reposListViewModel: ReposListViewModel
    private lateinit var repositoryImpl: ReposListRepositoryImpl
//    private lateinit var weatherObserver: Observer<Resource<Weather>>
    private val successResource = Resource.success(mutableListOf<GitReposModel>())
    private val errorResource: Resource<ResourceError> = Resource.error(ResourceError())



    @Test
    fun `when repository provides success`() {
        repositoryImpl = mock()
        runBlocking {
            whenever(repositoryImpl.getGitRepositories(isForceFetch)).thenReturn(successResource) }
    }


    @Test
    fun `when repository provides error`() {
        repositoryImpl = mock()
        runBlocking {
            whenever(repositoryImpl.getGitRepositories(isForceFetch)).thenReturn(errorResource) }
    }
}