package com.sample.gitrepos.network

class Resource<T>(val status: Status, val data: T?, val resourceError: ResourceError?) {
    enum class Status {
        SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(resourceError: ResourceError?): Resource<T> {
            return Resource(Status.ERROR, null, resourceError)
        }

//        fun <T> loading(): Resource<T> {
//            return Resource(Status.LOADING, null, null)
//        }
    }
}