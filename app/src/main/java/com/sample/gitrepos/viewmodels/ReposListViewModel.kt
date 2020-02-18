package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.gitrepos.models.GitReposListModel
import com.sample.gitrepos.usecases.ReposListUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject

class ReposListViewModel(mApplication: Application) : BaseViewModel(mApplication) {

    //    val reposListRepositoryImpl: ReposListRepositoryImpl by inject()
    private val reposListUsecase: ReposListUsecase by inject()
    private var reposListLiveData = MutableLiveData<GitReposListModel>()



    fun showReposData() {
        viewModelScope.launch(Dispatchers.Main) {
            val reposListModel = reposListUsecase.askGitRepositoriesData()
            reposListLiveData.postValue(reposListModel)
        }
    }
}