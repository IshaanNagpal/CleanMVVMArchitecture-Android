package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.usecases.ReposListResposListUseCaseImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReposListViewModelImpl(mApplication: Application, private val reposListUseCaseImpl: ReposListResposListUseCaseImpl) : BaseViewModel(mApplication), ReposListViewModel {


    private val reposListLiveData by lazy { MutableLiveData<List<ListItemModel>>() }

    override fun getReposLiveData(): MutableLiveData<List<ListItemModel>> = reposListLiveData


    init {
        observeForReposResponseLiveData()
    }

    private fun observeForReposResponseLiveData() {
        reposListUseCaseImpl.subscribeForReposData().observeForever {
            when(it.status) {

                Resource.Status.LOADING -> {setLoading()}

                Resource.Status.SUCCESS -> {
                    val reposItemViewList by lazy { mutableListOf<ListItemModel>() }
                    val reposListModel = it.data
                    reposListModel?.map { gitReposModel -> reposItemViewList.add(ReposItemView(gitReposModel))
                        reposListLiveData.postValue(reposItemViewList)
                        setSuccess()
                    }
                }

                Resource.Status.ERROR -> {setError()}
            }
        }
    }

    fun showReposData() {
        setLoading()
        viewModelScope.launch(Dispatchers.Main) {
            delay(3000)   //Added delay to show shimmer for 3s
            reposListUseCaseImpl.getDataFromRepository()
        }
    }

}