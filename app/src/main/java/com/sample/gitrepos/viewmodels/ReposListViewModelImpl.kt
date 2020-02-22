package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.gitrepos.models.GitReposModel
import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.usecases.ReposListUseCaseImpl
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.inject

class ReposListViewModelImpl(mApplication: Application) : BaseViewModel(mApplication), ReposListViewModel {

    private val reposListUseCaseImpl: ReposListUseCaseImpl by inject()
    private val reposListLiveData by lazy { MutableLiveData<List<ListItemModel>>() }


    override fun getReposLiveData(): MutableLiveData<List<ListItemModel>> = reposListLiveData


    fun showReposData() {
        setLoading()
        viewModelScope.launch(Dispatchers.Main) {
            delay(3000)   //Added delay to show shimmer for atleast 3s

            val reposItemViewList by lazy { mutableListOf<ListItemModel>() }

            when(reposListUseCaseImpl.getDataFromRepository().status){

                Resource.Status.SUCCESS -> {
                    val reposListModel = reposListUseCaseImpl.getDataFromRepository().data
                    if (reposListModel is MutableList<GitReposModel>) {
                        reposListModel.map {
                            reposItemViewList.add(ReposItemView(it))
                        }
                        reposListLiveData.postValue(reposItemViewList)
                        setSuccess()
                    }
                }

            }

        }
    }

}