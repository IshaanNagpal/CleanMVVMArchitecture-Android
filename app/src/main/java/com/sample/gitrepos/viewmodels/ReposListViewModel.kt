package com.sample.gitrepos.viewmodels

import android.app.Application
import com.sample.gitrepos.usecases.ReposListUsecase
import org.koin.core.inject

class ReposListViewModel(mApplication: Application) : BaseViewModel(mApplication){

//    val reposListRepositoryImpl: ReposListRepositoryImpl by inject()
    val reposListUsecase: ReposListUsecase by inject()


    public fun showReposData() {


        reposListUsecase.askGitRepositoriesData()
    }
}