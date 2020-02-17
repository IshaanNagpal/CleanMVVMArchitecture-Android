package com.sample.gitrepos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sample.gitrepos.R
import com.sample.gitrepos.viewmodels.ReposListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposListActivity : AppCompatActivity() {

    private val reposListViewModel: ReposListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
