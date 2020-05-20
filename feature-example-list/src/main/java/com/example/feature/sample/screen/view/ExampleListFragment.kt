package com.example.feature.sample.screen.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.core.view.adapter.*
import com.example.base.core.view.base.BaseFragment
import com.example.base.core.view.extension.observe
import com.example.base.core.view.extension.showToast
import com.example.base.core.view.model.Todo
import com.example.base.core.viewmodel.model.LoadingState
import com.example.base.core.viewmodel.model.isNone
import com.example.feature.sample.screen.R
import com.example.feature.sample.screen.viewmodel.ExampleListAction
import com.example.feature.sample.screen.viewmodel.ExampleListState
import com.example.feature.sample.screen.viewmodel.ExampleListViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_example_list.*
import javax.inject.Inject

class ExampleListFragment(
    override val layoutResourceId: Int = R.layout.fragment_example_list
) : BaseFragment() {

    @Inject
    lateinit var viewModel: ExampleListViewModel

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModelState()
        if (viewModel.stateValue.loadingState.isNone()) viewModel.perform(ExampleListAction.FetchTodos)
    }

    private fun setupRecyclerView() {
        example_list_recycler_view.apply {
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = this@ExampleListFragment.adapter
        }
    }

    private fun observeViewModelState() {
        observe(viewModel.state) { render(it) }
    }

    private fun render(state: ExampleListState) {
        when (state.loadingState) {
            LoadingState.NONE, LoadingState.LOADING -> renderLoadingState()
            LoadingState.ERROR -> renderErrorState()
            LoadingState.DONE -> if (state.todos.isEmpty()) renderEmptyState() else renderContentState(
                state
            )
        }
    }

    private fun renderLoadingState() {
        adapter.update(listOf(LoadingStateItem))
    }

    private fun renderEmptyState() {
        adapter.update(
            listOf(
                EmptyStateItem(
                    title = getString(R.string.example_list_empty_title),
                    subtitle = getString(R.string.example_list_empty_subtitle),
                    buttonText = getString(R.string.example_list_empty_button),
                    onButtonClick = { requireContext().showToast("Create todo") }
                )
            )
        )
    }

    private fun renderErrorState() {
        adapter.update(
            listOf(
                ErrorStateItem(
                    title = getString(R.string.example_list_error_title),
                    subtitle = getString(R.string.example_list_error_subtitle),
                    primaryActionText = getString(R.string.base_try_again),
                    primaryActionClick = { viewModel.perform(ExampleListAction.FetchTodos) }
                )
            )
        )
    }

    private fun renderContentState(state: ExampleListState) {
        adapter.update(state.todos.map { it.toRowItem() })
    }

    private fun Todo.toRowItem(): RowItem = RowItem(
        title = title
    )
}
