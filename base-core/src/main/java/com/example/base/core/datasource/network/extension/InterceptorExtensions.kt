package com.example.base.core.datasource.network.extension

import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

fun createQueryInterceptor(key: String, value: String): Interceptor = Interceptor { chain ->
    val request: Request = chain.request()
    val url: HttpUrl = request.url.newBuilder().addQueryParameter(key, value).build()
    return@Interceptor chain.proceed(request.newBuilder().url(url).build())
}

fun createHeaderInterceptor(key: String, value: String): Interceptor = Interceptor { chain ->
    val request: Request = chain.request()
    val headers: Headers = request.headers.newBuilder().add(key, value).build()
    return@Interceptor chain.proceed(request.newBuilder().headers(headers).build())
}

fun createLoggingInterceptor(level: HttpLoggingInterceptor.Level): Interceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = level
    return loggingInterceptor
}
