package com.example.library.todos.repository

import com.example.library.todos.datasource.network.api.ExampleTodosApiService
import com.example.library.todos.datasource.network.model.TodoResponse
import retrofit2.Response
import javax.inject.Inject

class TodosApiRepository @Inject constructor(
    private val api: ExampleTodosApiService
) {

    suspend fun todos(): List<TodoResponse> = api.todos()
}
