package com.example.base.core.datasource.network.extension

import com.example.base.core.exception.NetworkException
import retrofit2.Response

fun <T> Response<T>.check(): Response<T> {
    if (!isSuccessful) throw NetworkException(this)
    return this
}
