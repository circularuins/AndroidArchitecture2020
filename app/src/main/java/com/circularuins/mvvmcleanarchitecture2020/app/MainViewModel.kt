package com.circularuins.mvvmcleanarchitecture2020.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Result
import com.circularuins.mvvmcleanarchitecture2020.domain.repository.MasterRepository
import com.circularuins.mvvmcleanarchitecture2020.infra.repository.MasterRepositoryImpl
import kotlinx.coroutines.launch

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
            _progress.value = true
            when (val master = repository.getMaster()) {
                is Result.Success -> {
                    _masterData.value = master.data
                    Log.d("MasterData", "${master.data.size}件のデータ取得")
                }
                is Result.Error -> Log.d("MasterError", master.message)
            }
            _progress.value = false
        }
    }
}