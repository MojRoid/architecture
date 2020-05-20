package com.example.library.todos.datasource.network.authentication

import android.util.Base64
import com.example.base.core.global.Environment
import com.example.base.core.injection.scopes.PerApplication
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

private const val AUTH_KEY = "Authorization"
private const val AUTH_BASIC_PREFIX = "Basic "

@PerApplication
class ExampleBasicAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials =
            "${Environment.current.key.exampleKey}:${Environment.current.key.exampleKey}"
        val basic: String =
            AUTH_BASIC_PREFIX + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        val request: Request = chain.request()
        val headers: Headers = request.headers.newBuilder().add(AUTH_KEY, basic).build()

        return chain.proceed(request.newBuilder().headers(headers).build())
    }
}
