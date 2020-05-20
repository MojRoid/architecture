package com.example.base.core.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.base.core.extension.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseUseCase<RESULT, ARGUMENT> {

    private val className: String = this@BaseUseCase.javaClass.simpleName
    protected val state: MediatorLiveData<RESULT> = MediatorLiveData()
    val result: LiveData<RESULT> = state

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    fun execute(scope: CoroutineScope, argument: ARGUMENT) {
        scope.launch(handler) {
            val startTime: Long = System.currentTimeMillis()
            onExecute(argument)
            val duration: Double = (System.currentTimeMillis() - startTime) / 1000.0
            Timber.i("$className execution took $duration seconds")
        }.invokeOnCompletion { if (it is CancellationException) onCancel() }
    }

    abstract suspend fun onExecute(argument: ARGUMENT)

    protected open fun onError(throwable: Throwable) {
        throwable.log()
    }

    protected open fun onCancel() {
        Timber.i("${this@BaseUseCase.javaClass.simpleName} cancelled")
    }

    protected fun observe(liveData: LiveData<RESULT>) {
        state.addSource(liveData) { state.value = it }
    }

    protected fun setResult(result: RESULT) {
        Timber.i("$className result: $result")
        state.value = result
    }

    protected inline fun <reified T> observe(
        liveData: LiveData<T>,
        crossinline onChanged: (T) -> Unit
    ) {
        state.addSource(liveData) { t -> onChanged(t) }
    }
}
