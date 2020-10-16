package com.circularuins.mvvmcleanarchitecture2020.domain.repository

import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master
import com.circularuins.mvvmcleanarchitecture2020.domain.model.Result

interface MasterRepository {
     suspend fun getMaster(): Result<List<Master>>
}