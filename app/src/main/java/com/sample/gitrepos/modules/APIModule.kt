package com.sample.gitrepos.modules

import com.sample.gitrepos.network.FetchRepoAPIService
import org.koin.dsl.module
import retrofit2.Retrofit

val gitReposListModule = module{
    single { get<Retrofit>().create(FetchRepoAPIService::class.java) }
}