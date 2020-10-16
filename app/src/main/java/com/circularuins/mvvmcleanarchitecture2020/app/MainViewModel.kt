package com.circularuins.mvvmcleanarchitecture2020.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.domain.repository.MasterRepository
import com.circularuins.mvvmcleanarchitecture2020.infra.repository.MasterRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MainViewModel : ViewModel() {

    // FIXME: DIする
    private val repository: MasterRepository by lazy {
        MasterRepositoryImpl()
    }

    private val _masterData = MutableLiveData<List<Master>>()
    private val _progress = MutableLiveData<Boolean>(false)

    val masterData: LiveData<List<Master>> = _masterData;
    val progress: LiveData<Boolean> = _progress

    fun initTab() {
        viewModelScope.launch {
            // TODO: Result型に書き換える
            try {
                _progress.value = true

                val master = repository.getMaster()
                _masterData.value = master
            } catch (e: IOException) {
                Log.d("HTTPExample", "[NetWork Error] message: ${e.message}")
            } catch (e: HttpException) {
                Log.d("HTTPExample", "[API Error] code: ${e.code()}, message:: ${e.message}")
            } catch (e: Exception) {
                Log.d("HTTPExample", "[Unknown Error] message: ${e.message}")
            } finally {
                _progress.value = false
            }
        }
    }
}