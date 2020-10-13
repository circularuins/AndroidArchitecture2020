package com.circularuins.mvvmcleanarchitecture2020.infra.repository

import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.domain.repository.MasterRepository
import com.circularuins.mvvmcleanarchitecture2020.infra.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MasterRepositoryImpl : MasterRepository {

    // FIXME: DIする
    private val service : ApiService by lazy {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val moshiConverterFactory = MoshiConverterFactory.create(moshi)

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
        retrofit.create(ApiService::class.java)
    }

    override suspend fun getMaster(): List<Master> = service.getMaster()
}