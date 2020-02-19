package com.sample.gitrepos.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sample.gitrepos.R
import com.sample.gitrepos.utility.GenericAdapter
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.viewmodels.ReposListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReposListActivity : AppCompatActivity() {

    private val reposListViewModel: ReposListViewModel by viewModel()
    private lateinit var genericAdapter: GenericAdapter<ListItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecyclerView()
        registerObservers()
        reposListViewModel.showReposData()
    }


    private fun registerObservers() {
        reposListViewModel.getReposLiveData().observe(this, Observer {
            it?.let {
                setDataOnList(it)
            }
        })
    }

    private fun configureRecyclerView() {
        repos_recyclerview.layoutManager = LinearLayoutManager(this)
        if (repos_recyclerview.itemAnimator is SimpleItemAnimator)
            (repos_recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        repos_recyclerview.setHasFixedSize(true)
    }



    private fun setDataOnList(gitReposModelList: List<ListItemModel>) {
        if (!::genericAdapter.isInitialized) {
            genericAdapter = GenericAdapter(gitReposModelList)
            repos_recyclerview.adapter = genericAdapter
        } else {
            genericAdapter.updateItems(gitReposModelList)
        }
    }
}
