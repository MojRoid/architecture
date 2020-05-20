package com.example.library.todos.datasource.network.authentication

import com.example.base.core.global.Environment
import com.example.base.core.injection.scopes.PerApplication
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

private const val QUERY_KEY = "key"

@PerApplication
class ExampleApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(QUERY_KEY, Environment.current.key.exampleKey)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}
