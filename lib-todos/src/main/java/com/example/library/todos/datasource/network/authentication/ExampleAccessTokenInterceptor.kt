package com.example.library.todos.datasource.network.authentication

import com.example.base.core.datasource.network.constant.HttpCode
import com.example.base.core.extension.log
import com.example.base.core.extension.runOnMainThread
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.HttpException
import javax.inject.Inject

private const val AUTHORIZATION_KEY = "Authorization"
private const val BEARER_PREFIX = "Bearer"

class ExampleAccessTokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response: Response = createAuthenticatedRequest(chain)

        if (response.code.isUnauthorized()) {
            try {
                runBlocking {
                    // TODO: refresh access token synchronously
                }
            } catch (e: Exception) {
                e.log()
                if (e is HttpException && e.code().isUnauthorized()) {
                    runOnMainThread {
                        // TODO: clear settings, logout, get new refresh token etc.
                    }
                }
            }
        } else return response

        return createAuthenticatedRequest(chain)
    }

    private fun createAuthenticatedRequest(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val headers: Headers = request.headers
            .newBuilder()
            .add(
                AUTHORIZATION_KEY,
                "$BEARER_PREFIX ${"dynamically add correct access token"}"
            )
            .build()
        return chain.proceed(request.newBuilder().headers(headers).build())
    }

    private fun Int.isUnauthorized(): Boolean =
        this == HttpCode.BAD_REQUEST || this == HttpCode.UNAUTHORIZED || this == HttpCode.FORBIDDEN
}
