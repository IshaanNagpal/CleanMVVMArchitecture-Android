package com.sample.gitrepos.repositories

import com.sample.gitrepos.network.Resource
import com.sample.gitrepos.network.ResourceError
import okhttp3.ResponseBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

open class BaseRepository : KoinComponent {


    private val retrofit: Retrofit by inject()

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Resource<T> {

        val response = call.invoke()

        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(parseError(response.errorBody()))
        }
    }


    private suspend fun parseError(responseErrorBody: ResponseBody?): ResourceError {

        val converter: Converter<ResponseBody, ResourceError> =
            retrofit.responseBodyConverter(ResourceError::class.java, arrayOfNulls<Annotation>(0))
        try {
            responseErrorBody?.let {
                return converter.convert(it) ?: ResourceError()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ResourceError()
    }
}