package com.example.feature.sample.screen.viewmodel

import com.example.base.core.viewmodel.base.BaseViewModel
import com.example.base.core.viewmodel.model.LoadingState
import com.example.library.todos.usecase.FetchTodosUseCase
import javax.inject.Inject

class ExampleListViewModel(
    private val fetchTodosUseCase: FetchTodosUseCase
) : BaseViewModel<ExampleListAction, ExampleListState>(ExampleListState()) {

    class Factory @Inject constructor(
        fetchTodosUseCase: FetchTodosUseCase
    ) : BaseViewModel.Factory(
        ExampleListViewModel(
            fetchTodosUseCase
        )
    )

    init {
        observe(fetchTodosUseCase.result)
    }

    override fun onAction(action: ExampleListAction, state: ExampleListState) {
        when (action) {
            ExampleListAction.FetchTodos -> {
                state.copy(loadingState = LoadingState.LOADING).updateState()
                fetchTodosUseCase.execute()
            }
        }
    }

    override fun onResult(result: Any, state: ExampleListState): ExampleListState = when (result) {
        is FetchTodosUseCase.Result -> onFetchTodosResult(result, state)
        else -> state
    }

    private fun onFetchTodosResult(
        result: FetchTodosUseCase.Result,
        state: ExampleListState
    ): ExampleListState = when (result) {
        is FetchTodosUseCase.Result.Success -> state.copy(
            loadingState = LoadingState.DONE,
            todos = result.todos
        )
        FetchTodosUseCase.Result.Error -> state.copy(
            loadingState = LoadingState.ERROR
        )
    }
}
