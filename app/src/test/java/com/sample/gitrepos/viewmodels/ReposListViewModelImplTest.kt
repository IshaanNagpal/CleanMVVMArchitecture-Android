package com.sample.gitrepos.viewmodels


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.sample.gitrepos.CoroutineTest
import com.sample.gitrepos.extensions.toSafeString
import com.sample.gitrepos.getValue
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ReposListViewModelImplTest : CoroutineTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val reposListViewModelImpl: ReposListViewModelImpl by inject()
    private val reposListUseCaseImpl: ReposListUseCaseImpl = declareMock { }
    private val errorResource = Resource.error<ResourceError>(ResourceError())
    private val successResource = Resource.success(mutableListOf<GitReposModel>())

    @Mock
    private lateinit var viewStateObserver: Observer<List<ListItemModel>>


    @Test
    fun `is fetchResponseLiveData emitting when blank value is provided, with  null initially`() {
        Assert.assertNull(getValue(reposListViewModelImpl.getReposLiveData()))
        reposListViewModelImpl.getReposLiveData().value = listOf()
        Assert.assertNotNull(getValue(reposListViewModelImpl.getReposLiveData()))
        Assert.assertTrue((reposListViewModelImpl.getReposLiveData().value as List).isEmpty())
    }

    @Test
    fun `is fetchResponseLiveData emitting when an object is provided, with null initially`() {
        Assert.assertNull(getValue(reposListViewModelImpl.getReposLiveData()))
        val gitReposModel = GitReposModel(
            "author",
            "",
            forks = 1,
            language = "Kotlin",
            languageColor = "#ddd",
            name = "Demo",
            stars = 5,
            url = ""
        )
        val repoItemView = ReposItemView(gitReposModel)
        val itemList = listOf(repoItemView)
        reposListViewModelImpl.getReposLiveData().value = itemList
        Assert.assertNotNull(getValue(reposListViewModelImpl.getReposLiveData()))
        Assert.assertTrue((reposListViewModelImpl.getReposLiveData().value as List).isNotEmpty())
        Assert.assertEquals(gitReposModel.author, ((reposListViewModelImpl.getReposLiveData().value as List)[0] as ReposItemView).author)
        Assert.assertEquals(gitReposModel.stars.toSafeString(), ((reposListViewModelImpl.getReposLiveData().value as List)[0] as ReposItemView).stars)
    }


    @Test
    fun `verify for viewmodel calling usecase will return success`() {
        runBlocking {
            //Given
            given(reposListUseCaseImpl.getDataFromRepository(true)).will { successResource }
            //When
            val successResource = reposListUseCaseImpl.getDataFromRepository(true)
            //Then
            verify(reposListUseCaseImpl).getDataFromRepository(true)
            Assert.assertTrue(successResource.status == Resource.Status.SUCCESS)
        }
    }


    @Test
    fun `verify for viewmodel calling usecase will return error`() {
        runBlocking {
            //Given
            given(reposListUseCaseImpl.getDataFromRepository(false)).will { errorResource }
            //When
            val errorResource = reposListUseCaseImpl.getDataFromRepository(false)
            //Then
            verify(reposListUseCaseImpl).getDataFromRepository(false)
            Assert.assertTrue(errorResource.status == Resource.Status.ERROR)
        }
    }


    @Test
    fun `success state observer is notified when setSuccess is called`() {
        //Default observer value
        Assert.assertFalse(reposListViewModelImpl.isSuccessState().get() ?: false)
        //Success state observer notified
        reposListViewModelImpl.setSuccess()
        //Verify only success observer is notified with new value
        Assert.assertTrue(reposListViewModelImpl.isSuccessState().get() ?: false)
        Assert.assertFalse(reposListViewModelImpl.isLoadingState().get() ?: false)
        Assert.assertFalse(reposListViewModelImpl.isErrorState().get() ?: false)
    }

    @Test
    fun `loading state observer is notified when setLoading is called`() {
        //Default observer value
        Assert.assertFalse(reposListViewModelImpl.isLoadingState().get() ?: false)
        //Loading state observer notified
        reposListViewModelImpl.setLoading()
        //Verify only loading observer is notified with new value
        Assert.assertFalse(reposListViewModelImpl.isSuccessState().get() ?: false)
        Assert.assertTrue(reposListViewModelImpl.isLoadingState().get() ?: false)
        Assert.assertFalse(reposListViewModelImpl.isErrorState().get() ?: false)
    }

    @Test
    fun `error state observer is notified when setError is called`() {
        //Default observer value
        Assert.assertFalse(reposListViewModelImpl.isErrorState().get() ?: false)
        //Error state observer notified
        reposListViewModelImpl.setError()
        //Verify only error observer is notified with new value
        Assert.assertFalse(reposListViewModelImpl.isSuccessState().get() ?: false)
        Assert.assertFalse(reposListViewModelImpl.isLoadingState().get() ?: false)
        Assert.assertTrue(reposListViewModelImpl.isErrorState().get() ?: false)
    }


}