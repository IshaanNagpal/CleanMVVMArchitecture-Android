package com.sample.gitrepos.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sample.gitrepos.R
import com.sample.gitrepos.databinding.ActivityMainBinding
import com.sample.gitrepos.utility.ExpandAndCollapseAdapter
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.viewmodels.ReposListViewModelImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReposListActivity : BaseActivity() {

    private val reposListViewModelImpl: ReposListViewModelImpl by viewModel()
    private lateinit var genericAdapter: ExpandAndCollapseAdapter<ListItemModel>
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = reposListViewModelImpl
        configureRecyclerView()
        configurePullToRefresh()
        registerObservers()
        fetchRepositories()
    }

    private fun fetchRepositories() {
        GlobalScope.launch(Dispatchers.Main) {
            shimmer_view_container.startShimmerAnimation()
        }
        reposListViewModelImpl.showReposData()
    }

    private fun configurePullToRefresh() {
        swipe_container.setOnRefreshListener {
            fetchRepositories()
        }
    }


    private fun registerObservers() {
        reposListViewModelImpl.getReposLiveData().observe(this, Observer {
            it?.let {
                shimmer_view_container.stopShimmerAnimation()
                setDataOnList(it)
            }
        })
    }

    private fun configureRecyclerView() {
        repos_recyclerview.layoutManager = LinearLayoutManager(this)
        repos_recyclerview.addItemDecoration(DividerItemDecoration(repos_recyclerview.context, 1))
        if (repos_recyclerview.itemAnimator is SimpleItemAnimator)
            (repos_recyclerview.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = true
        repos_recyclerview.setHasFixedSize(true)
    }



    private fun setDataOnList(gitReposModelList: List<ListItemModel>) {
        if (!::genericAdapter.isInitialized) {
            genericAdapter = ExpandAndCollapseAdapter(gitReposModelList, null)
            repos_recyclerview.adapter = genericAdapter
        } else {
            genericAdapter.updateItems(gitReposModelList)
        }
    }



}
