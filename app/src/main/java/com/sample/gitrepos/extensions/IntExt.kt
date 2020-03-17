package com.sample.gitrepos.extensions

import java.lang.Exception

fun Int?.toSafeString(): String {
    this?.let {
        return try {
            this.toString()
        } catch (e: Exception) {
            ""
        }
    }
    return ""
}