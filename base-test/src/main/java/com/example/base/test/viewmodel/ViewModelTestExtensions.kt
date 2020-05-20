package com.example.base.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.base.test.usecase.TestUseCase
import com.example.base.core.usecase.BaseUseCase
import com.example.base.core.usecase.BaseUseCaseNoArg
import com.example.base.core.viewmodel.base.BaseViewModel
import org.mockito.BDDMockito.then

fun <T> BaseViewModel<*, *>.givenObservedData(data: T) {
    val liveData: MutableLiveData<T> = MutableLiveData()
    observe(liveData)
    liveData.value = data
}

fun <T : BaseUseCase<RESULT, ARGUMENT>, RESULT, ARGUMENT> TestUseCase<T, RESULT, ARGUMENT>.givenResult(
    result: RESULT
) {
    this.result.value = result
}

fun <ACTION> BaseViewModel<ACTION, *>.whenActionIsPerformed(action: ACTION) {
    perform(action)
}

fun <STATE> Observer<STATE>.thenObserverShouldReceiveCorrectStates(vararg states: STATE) {
    states.forEach { then(this).should().onChanged(it) }
    then(this).shouldHaveNoMoreInteractions()
}

fun <T : BaseUseCaseNoArg<RESULT>, RESULT> BaseViewModel<*, *>.thenUseCaseShouldBeExecuted(
    testUseCase: TestUseCase<T, RESULT, Unit>
) {
    then(testUseCase.useCase).should().result
    then(testUseCase.useCase).should().execute(this)
    then(testUseCase.useCase).shouldHaveNoMoreInteractions()
}

fun TestUseCase<*, *, *>.thenUseCaseShouldNotBeExecuted() {
    then(useCase).should().result
    then(useCase).shouldHaveNoMoreInteractions()
}

fun <T : BaseUseCase<RESULT, ARGUMENT>, RESULT, ARGUMENT> BaseViewModel<*, *>.thenUseCaseShouldBeExecuted(
    testUseCase: TestUseCase<T, RESULT, ARGUMENT>,
    argument: ARGUMENT
) {
    then(testUseCase.useCase).should().result
    then(testUseCase.useCase).should().execute(this, argument)
    then(testUseCase.useCase).shouldHaveNoMoreInteractions()
}
