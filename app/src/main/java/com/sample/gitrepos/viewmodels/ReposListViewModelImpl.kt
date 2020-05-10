package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.sample.gitrepos.extensions.toSingleEvent
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.utility.REPOS_ACTIVITY_STATE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This ViewModel class is responsible for maintaining the Business Logic for the Git Repos Screen
 */
class ReposListViewModelImpl(mApplication: Application, private val reposListUseCaseImpl: ReposListUseCaseImpl, private val savedStateHandle: SavedStateHandle) :
    BaseViewModel(mApplication), ReposListViewModel {

    private lateinit var refreshLabel: MutableLiveData<String>

    //This live data to be listened by view as the owner is Viewmodel
    override fun getReposLiveData(): MutableLiveData<List<ListItemModel>> =
        savedStateHandle.getLiveData(REPOS_ACTIVITY_STATE)

    override fun getReposRefreshLabel(): MutableLiveData<String> {
        if(!::refreshLabel.isInitialized) {
            refreshLabel = MutableLiveData()
        }

        return refreshLabel.toSingleEvent()
    }


    fun getReposData(forceFetch: Boolean = false) {

        setLoading()
        viewModelScope.launch {
            delay(3000) //Delay added to show shimmer for 3s
            val resource = reposListUseCaseImpl.getDataFromRepository(forceFetch)
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    val reposItemViewList = reposListUseCaseImpl.mapDataToViewItems(resource.data)
                    savedStateHandle.set(REPOS_ACTIVITY_STATE, reposItemViewList)
                    refreshLabel.value = reposListUseCaseImpl.getLastRefreshedString()
                    setSuccess()
                }

                Resource.Status.ERROR -> {
                    setError()
                }
            }
        }
    }

    fun swipeToRefreshCalled() {
        getReposData(true)
    }

}
