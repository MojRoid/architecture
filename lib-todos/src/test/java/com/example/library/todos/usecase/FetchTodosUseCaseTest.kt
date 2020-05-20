package com.example.library.todos.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.base.test.stub.TodoStubs
import com.example.library.todos.datasource.network.model.TodoResponse
import com.example.library.todos.repository.TodosApiRepository
import com.example.library.todos.usecase.FetchTodosUseCase.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

val TODO_RESPONSE_1 = TodoResponse(
    id = 1,
    userId = 1,
    title = "TITLE 1",
    completed = true
)

val TODO_RESPONSE_2 = TodoResponse(
    id = 2,
    userId = 2,
    title = "TITLE 2",
    completed = false
)

val RESPONSE_LIST: List<TodoResponse> = listOf(TODO_RESPONSE_1, TODO_RESPONSE_2)

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchTodosUseCaseTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Result>

    @Mock
    private lateinit var repository: TodosApiRepository

    private val scope = TestCoroutineScope()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var useCase: FetchTodosUseCase

    @Before
    fun setup() {
        useCase = FetchTodosUseCase(dispatcher, repository)
        useCase.result.observeForever(observer)
    }

    @After
    fun teardown() {
        scope.cleanupTestCoroutines()
        dispatcher.cleanupTestCoroutines()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `execute - repository returns data should produce content result`() {
        givenRepositoryResult(RESPONSE_LIST)
        whenUseCaseIsExecuted()
        thenObserverShouldReceiveCorrectResults(Result.Success(TodoStubs.LIST))
    }

    @Test
    fun `execute - repository throws exception should produce error result`() {
        givenRepositoryThrowsException()
        whenUseCaseIsExecuted()
        thenObserverShouldReceiveCorrectResults(Result.Error)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenRepositoryResult(list: List<TodoResponse>) {
        runBlockingTest { given(repository.todos()).willReturn(list) }
    }

    private fun givenRepositoryThrowsException() {
        runBlockingTest { given(repository.todos()).willAnswer { throw Exception() } }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenUseCaseIsExecuted() {
        useCase.execute(scope)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenObserverShouldReceiveCorrectResults(vararg results: Result) {
        results.forEach { then(observer).should().onChanged(it) }
        then(observer).shouldHaveNoMoreInteractions()
    }
}
