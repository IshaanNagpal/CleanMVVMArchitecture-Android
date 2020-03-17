package com.sample.gitrepos.utility

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class AppUtility(private val application: Application) {

    fun isInternetAvailable(): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }
}