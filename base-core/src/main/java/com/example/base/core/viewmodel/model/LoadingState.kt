package com.example.base.core.viewmodel.model

enum class LoadingState {

    NONE,
    LOADING,
    DONE,
    ERROR
}

fun LoadingState.isNone(): Boolean = this == LoadingState.NONE
fun LoadingState.isLoading(): Boolean = this == LoadingState.LOADING
fun LoadingState.isDone(): Boolean = this == LoadingState.DONE
fun LoadingState.isError(): Boolean = this == LoadingState.ERROR