package com.sample.gitrepos.extensions

import java.lang.Exception

fun Int.toSafeString(): String {
    return try {
        this.toString()
    } catch (e: Exception) {
        ""
    }
}