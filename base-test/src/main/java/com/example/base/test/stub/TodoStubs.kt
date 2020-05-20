package com.example.base.test.stub

import com.example.base.core.view.model.Todo

object TodoStubs {

    val TODO_1 = Todo(
        id = 1,
        userId = 1,
        title = "TITLE 1",
        completed = true
    )

    val TODO_2 = Todo(
        id = 2,
        userId = 2,
        title = "TITLE 2",
        completed = false
    )

    val LIST: List<Todo> = listOf(TODO_1, TODO_2)
}
