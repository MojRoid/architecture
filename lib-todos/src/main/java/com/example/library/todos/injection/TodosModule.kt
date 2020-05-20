package com.example.library.todos.injection

import com.example.base.core.global.Environment
import com.example.base.core.injection.scopes.PerApplication
import com.example.library.todos.datasource.network.api.ExampleTodosApiService
import com.example.library.todos.datasource.network.authentication.ExampleBasicAuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class TodosModule {

    @Provides
    @PerApplication
    fun provideExampleUserApiService(
        builder: OkHttpClient.Builder,
        authenticationInterceptor: ExampleBasicAuthInterceptor
    ): ExampleTodosApiService =
        Retrofit.Builder().client(builder.addInterceptor(authenticationInterceptor).build())
            .baseUrl(Environment.current.endpoint.exampleApi)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(ExampleTodosApiService::class.java)
}
