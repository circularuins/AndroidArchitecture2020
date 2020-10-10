package com.circularuins.mvvmcleanarchitecture2020.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.infra.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.lang.Exception

class MainViewModel : ViewModel() {

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

    private val _masterData = MutableLiveData<List<Master>>()
    val masterData: LiveData<List<Master>> = _masterData;

    fun initTab() {
        viewModelScope.launch {
            try {
                val master = service.getMaster()
                _masterData.postValue(master)
            } catch (e: IOException) {
                Log.d("HTTPExample", "[NetWork Error] message: ${e.message}")
            } catch (e: HttpException) {
                Log.d("HTTPExample", "[API Error] code: ${e.code()}, message:: ${e.message}")
            } catch (e: Exception) {
                Log.d("HTTPExample", "[Unknown Error] message: ${e.message}")
            }
        }
    }
}