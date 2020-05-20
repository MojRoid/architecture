package com.example.base.core.injection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.base.core.BuildConfig
import com.example.base.core.datasource.network.extension.createLoggingInterceptor
import com.example.base.core.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class BaseModule {

    @Provides
    @PerApplication
    fun provideLogger(): Timber.Tree = Timber.DebugTree()

    @Provides
    @PerApplication
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)

    @Provides
    @PerApplication
    fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @PerApplication
    fun provideMainCoroutineDispatcher(): MainCoroutineDispatcher = Dispatchers.Main

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(
            createLoggingInterceptor(HttpLoggingInterceptor.Level.BODY)
        )
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.writeTimeout(15, TimeUnit.SECONDS)
        return builder
    }
}
