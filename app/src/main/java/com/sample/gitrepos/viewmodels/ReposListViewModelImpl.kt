package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.utility.REPOS_ACTIVITY_STATE
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This ViewModel class is responsible for maintaining the Business Logic for the Git Repos Screen
 */
class ReposListViewModelImpl(
    mApplication: Application,
    private val reposListUseCaseImpl: ReposListUseCaseImpl,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel(mApplication), ReposListViewModel {

    //This live data to be listened by view as the owner is Viewmodel
    private val reposListLiveData by lazy { MutableLiveData<List<ListItemModel>>() }

    override fun getReposLiveData(): MutableLiveData<List<ListItemModel>> =
        savedStateHandle.getLiveData(REPOS_ACTIVITY_STATE)


    fun getReposData(forceFetch: Boolean = false) {
        setLoading()
        viewModelScope.launch(Dispatchers.Main) {
            delay(3000) //Delay added to show shimmer for 3s
            val resource = reposListUseCaseImpl.getDataFromRepository(forceFetch)
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    val reposItemViewList = getItemViewsFromData(resource.data)
                    savedStateHandle.set(REPOS_ACTIVITY_STATE, reposItemViewList)
                    reposListLiveData.postValue(reposItemViewList)
                    setSuccess()
                }

                Resource.Status.ERROR -> {
                    setError()
                }
            }
        }
    }

    private fun getItemViewsFromData(reposList: MutableList<GitReposModel>?): List<ListItemModel> {
        val reposItemViewList = mutableListOf<ListItemModel>()
        reposList?.map { gitReposModel ->
            reposItemViewList.add(ReposItemView(gitReposModel))
        }
        return reposItemViewList
    }

    fun swipeToRefreshCalled() {
        getReposData(true)
    }

}