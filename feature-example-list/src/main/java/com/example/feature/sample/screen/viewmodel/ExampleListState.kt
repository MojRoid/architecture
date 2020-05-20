package com.example.feature.sample.screen.viewmodel

import com.example.base.core.view.model.Todo
import com.example.base.core.viewmodel.model.LoadingState

data class ExampleListState(
    val loadingState: LoadingState = LoadingState.NONE,
    val todos: List<Todo> = emptyList()
)
