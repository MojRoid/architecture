package com.example.library.todos.usecase

import com.example.base.core.usecase.BaseUseCaseNoArg
import com.example.base.core.view.model.Todo
import com.example.library.todos.datasource.network.model.TodoResponse
import com.example.library.todos.repository.TodosApiRepository
import com.example.library.todos.usecase.FetchTodosUseCase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FetchTodosUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val todosApiRepository: TodosApiRepository
) : BaseUseCaseNoArg<Result>() {

    sealed class Result {
        data class Success(val todos: List<Todo>) : Result()
        object Error : Result()
    }

    override suspend fun onExecute() {
        withContext(dispatcher) {
            val response: List<TodoResponse> = todosApiRepository.todos()
            Result.Success(todos = response.toTodos())
        }.run { setResult(this) }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        setResult(Result.Error)
    }

    private fun List<TodoResponse>?.toTodos(): List<Todo> =
        this?.map { it.toTodo() } ?: emptyList()

    private fun TodoResponse.toTodo() = Todo(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )
}
