package com.circularuins.mvvmcleanarchitecture2020.infra

import com.circularuins.mvvmcleanarchitecture2020.domain.model.Item
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"
    }

    @GET("master.json")
    suspend fun getMaster(): List<Master>

    @GET("{type}.json")
    suspend fun getItems(@Path("type") type: String): List<Item>
}