package com.sample.gitrepos.views

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun removeObservers()
    abstract fun addObservers()

    override fun onStart() {
        super.onStart()
        addObservers()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
    }

}