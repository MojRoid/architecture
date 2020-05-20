package com.example.feature.example.notes.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.base.core.viewmodel.model.LoadingState
import com.example.base.test.stub.TodoStubs
import com.example.base.test.usecase.TestUseCase
import com.example.base.test.viewmodel.givenResult
import com.example.base.test.viewmodel.thenObserverShouldReceiveCorrectStates
import com.example.base.test.viewmodel.thenUseCaseShouldBeExecuted
import com.example.base.test.viewmodel.whenActionIsPerformed
import com.example.feature.sample.screen.viewmodel.ExampleListAction
import com.example.feature.sample.screen.viewmodel.ExampleListState
import com.example.feature.sample.screen.viewmodel.ExampleListViewModel
import com.example.library.todos.usecase.FetchTodosUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ExampleListViewModelTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<ExampleListState>

    private lateinit var viewModel: ExampleListViewModel

    private val fetchTodosUseCase = TestUseCase(FetchTodosUseCase::class)

    @Before
    fun setup() {
        setupTestUseCases()
        viewModel = ExampleListViewModel(
            fetchTodosUseCase.useCase
        )
        viewModel.state.observeForever(observer)
    }

    private fun setupTestUseCases() {
        fetchTodosUseCase.setup()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `action - FetchTodos`() {
        viewModel.whenActionIsPerformed(ExampleListAction.FetchTodos)
        viewModel.thenUseCaseShouldBeExecuted(fetchTodosUseCase)
    }

    @Test
    fun `state - LoadNextPlanNodeDatabaseUseCase - Content result`() {
        fetchTodosUseCase.givenResult(FetchTodosUseCase.Result.Success(TodoStubs.LIST))
        observer.thenObserverShouldReceiveCorrectStates(
            ExampleListState(),
            ExampleListState(
                loadingState = LoadingState.DONE,
                todos = TodoStubs.LIST
            )
        )
    }
}
