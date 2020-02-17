package com.sample.gitrepos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

open class BaseViewModel(application: Application) : AndroidViewModel(application), KoinComponent {


}