package com.example.base.core.usecase

import kotlinx.coroutines.CoroutineScope

abstract class BaseUseCaseNoArg<RESULT> : BaseUseCase<RESULT, Unit>() {

    fun execute(scope: CoroutineScope) {
        execute(scope, Unit)
    }

    override suspend fun onExecute(argument: Unit) {
        onExecute()
    }

    abstract suspend fun onExecute()
}
