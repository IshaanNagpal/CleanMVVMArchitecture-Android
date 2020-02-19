package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.gitrepos.usecases.ReposListUsecase
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject

class ReposListViewModel(mApplication: Application) : BaseViewModel(mApplication) {
    private var reposListLiveData = MutableLiveData<List<ListItemModel>>()
    private val reposListUsecase: ReposListUsecase by inject()


    fun showReposData() {
        viewModelScope.launch(Dispatchers.Main) {
            val reposItemViewList by lazy { mutableListOf<ListItemModel>() }
            val reposListModel = reposListUsecase.askGitRepositoriesData()
            reposListModel.map {
                reposItemViewList.add(ReposItemView(it))
            }
            reposListLiveData.postValue(reposItemViewList)
        }
    }


    fun getReposLiveData(): MutableLiveData<List<ListItemModel>> {
        return reposListLiveData
    }
}