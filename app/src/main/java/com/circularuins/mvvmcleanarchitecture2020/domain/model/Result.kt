package com.circularuins.mvvmcleanarchitecture2020.domain.model

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

fun Exception.convertErrorType() : Result.Error =
    when (this) {
        is IOException -> Result.Error("[NetWork Error] message: $message")
        is HttpException -> Result.Error("[API Error] code: ${code()}, message:: $message")
        else -> Result.Error("[Unknown Error] message: $message")
    }