package com.example.base.core.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.base.core.usecase.BaseUseCase
import com.example.base.core.usecase.BaseUseCaseNoArg
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<ACTION, STATE>(
    private val initialState: STATE
) : ViewModel(), CoroutineScope {

    abstract class Factory(private val viewModel: ViewModel) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(c: Class<T>): T = viewModel as T
    }

    private val mediator: MediatorLiveData<STATE> = MediatorLiveData()
    val state: LiveData<STATE> = mediator
    val stateValue: STATE get() = state.value ?: initialState

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext = CoroutineScope(Dispatchers.Main + job)
        .coroutineContext

    init {
        mediator.value = initialState
    }

    override fun onCleared() {
        job.cancelChildren()
    }

    /**
     * Call with an action to be performed.
     */
    fun perform(action: ACTION) {
        onAction(action, stateValue)
    }

    /**
     * Call to only update state if it has changed.
     */
    fun STATE.updateState() {
        takeIf { it != mediator.value }?.let { mediator.value = it }
    }

    /**
     * Called when the view performs an action.
     * The current state is also supplied.
     * Override to act on performed actions, i.e. to execute a use-case.
     */
    protected open fun onAction(action: ACTION, state: STATE) = Unit

    /**
     * Shorthand extension function to execute a use-case.
     */
    protected fun <RESULT, ARGUMENT> BaseUseCase<RESULT, ARGUMENT>.execute(argument: ARGUMENT) {
        execute(this@BaseViewModel, argument)
    }

    /**
     * Shorthand extension function to execute a no-arg use-case.
     */
    protected fun <RESULT> BaseUseCaseNoArg<RESULT>.execute() {
        execute(this@BaseViewModel)
    }

    /**
     * Observe on any piece of live data.
     */
    fun observe(result: LiveData<*>) {
        mediator.addSource(result) {
            onResult(it, stateValue).updateState()
        }
    }

    /**
     * Called on every result change from an observed live data.
     * The current state is supplied.
     * Override to act on result changes.
     *
     * @return the new state.
     */
    protected open fun onResult(result: Any, state: STATE): STATE = state
}
