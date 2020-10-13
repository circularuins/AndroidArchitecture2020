package com.circularuins.mvvmcleanarchitecture2020.domain.repository

import com.circularuins.mvvmcleanarchitecture2020.domain.model.Master

interface MasterRepository {
     suspend fun getMaster(): List<Master>
}