package com.sample.gitrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import com.sample.gitrepos.utility.ListItemModel

interface ReposListViewModel {
    fun getReposLiveData(): MutableLiveData<List<ListItemModel>>

}