package com.example.feature.tester.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.core.view.adapter.RowItem
import com.example.base.core.view.adapter.ViewHolder
import com.example.base.core.view.base.BaseFragment
import com.example.base.core.view.extension.observe
import com.example.feature.tester.R
import com.example.feature.tester.viewmodel.TesterState
import com.example.feature.tester.viewmodel.TesterViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_tester.*
import javax.inject.Inject

class TesterFragment(
    override val layoutResourceId: Int = R.layout.fragment_tester
) : BaseFragment() {

    @Inject
    lateinit var viewModel: TesterViewModel

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListActions()
        observeViewModelState()
    }

    private fun setupRecyclerView() {
        tester_recycler_view.apply {
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = this@TesterFragment.adapter
        }
    }

    private fun setupListActions() {
        adapter.update(listOf(
            RowItem(description = "MAIN ACTIVITY") { findNavController().navigate(R.id.TesterFragment_to_MainActivity) },
            RowItem(description = "TYPOGRAPHY FRAGMENT") { findNavController().navigate(R.id.TesterFragment_to_TypographyFragment) }
        ))
    }

    private fun observeViewModelState() {
        observe(viewModel.state) { render(it) }
    }

    private fun render(state: TesterState) {

    }
}
