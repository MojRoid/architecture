package com.example.library.todos.datasource.network.api

import com.example.library.todos.datasource.network.model.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExampleTodosApiService {

    @GET("todos")
    suspend fun todos(): List<TodoResponse>
}
