package com.example.feature.sample.screen.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.core.view.adapter.RowItem
import com.example.base.core.view.adapter.ViewHolder
import com.example.base.core.view.base.BaseFragment
import com.example.base.core.view.extension.observe
import com.example.feature.sample.screen.R
import com.example.feature.sample.screen.viewmodel.SampleScreenState
import com.example.feature.sample.screen.viewmodel.SampleScreenViewModel
import com.xwray.groupie.GroupAdapter
import kotlinx.android.synthetic.main.fragment_sample_screen.*
import javax.inject.Inject

class SampleScreenFragment(
    override val layoutResourceId: Int = R.layout.fragment_sample_screen
) : BaseFragment() {

    @Inject
    lateinit var viewModel: SampleScreenViewModel

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeViewModelState()
    }

    private fun setupToolbar() {
        sample_screen_toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun setupRecyclerView() {
        sample_screen_recycler_view.apply {
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = this@SampleScreenFragment.adapter
        }
    }

    private fun observeViewModelState() {
        observe(viewModel.state) { render(it) }
    }

    private fun render(state: SampleScreenState) {
        adapter.update(
            listOf(
                RowItem(description = javaClass.simpleName)
            )
        )
    }
}
