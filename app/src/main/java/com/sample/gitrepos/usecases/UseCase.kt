package com.sample.gitrepos.usecases

import com.sample.gitrepos.network.Resource
import org.koin.core.KoinComponent

interface UseCase<T> : KoinComponent {
    suspend fun getDataFromRepository(): Resource<T>
}