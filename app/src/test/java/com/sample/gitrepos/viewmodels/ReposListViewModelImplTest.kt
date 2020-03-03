package com.sample.gitrepos.viewmodels


import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.sample.gitrepos.BaseTest
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ReposListViewModelImplTest : CoroutineTest() {

    private val reposListViewModelImpl: ReposListViewModelImpl by inject()
    private val reposListUseCaseImpl: ReposListUseCaseImpl = declareMock {  }
    private val errorResource = Resource.error<ResourceError>(ResourceError())
    private val successResource = Resource.success(mutableListOf<GitReposModel>())

    @Test
    fun `is fetchResponseLiveData is holding value`() {
        reposListViewModelImpl.getReposLiveData().value = listOf()
        Assert.assertTrue((reposListViewModelImpl.getReposLiveData().value as List).isEmpty())
    }


    @Test
    fun `verify for viewmodel calling usecase will return success`() {

        runBlocking {

            //Given
            given(reposListUseCaseImpl.getDataFromRepository( true)).will{ successResource }
            given(reposListUseCaseImpl.getDataFromRepository(false)).will{ errorResource }
            //When
            val successResource = reposListUseCaseImpl.getDataFromRepository(true)
            val errorResource = reposListUseCaseImpl.getDataFromRepository(false)
            //Then
            verify(reposListUseCaseImpl).getDataFromRepository(true)
            Assert.assertTrue(successResource.status == Resource.Status.SUCCESS)
            Assert.assertTrue(errorResource.status == Resource.Status.ERROR)
        }
    }


}