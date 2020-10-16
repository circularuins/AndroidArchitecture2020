package com.circularuins.mvvmcleanarchitecture2020.infra.repository

import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Result
import com.circularuins.mvvmcleanarchitecture2020.domain.repository.MasterRepository
import com.circularuins.mvvmcleanarchitecture2020.infra.ApiService
import com.circularuins.mvvmcleanarchitecture2020.infra.convert
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.lang.Exception

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

    override suspend fun getMaster(): Result<List<Master>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val master = service.getMaster().map { master -> master.convert() }
                Result.Success(master)
            } catch (e: IOException) {
                Result.Error("[NetWork Error] message: ${e.message}")
            } catch (e: HttpException) {
                Result.Error("[API Error] code: ${e.code()}, message:: ${e.message}")
            } catch (e: Exception) {
                Result.Error("[Unknown Error] message: ${e.message}")
            }

        }
}