package com.sample.gitrepos.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.gitrepos.utility.LiveEvent

fun <T> MutableLiveData<T>.toSingleEvent(): MutableLiveData<T> {
    val result = LiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}