package com.example.base.core.view.adapter

import android.view.View
import androidx.core.view.isVisible
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_error_state.view.*

data class ErrorStateItem(
    private val title: String,
    private val subtitle: String,
    private val primaryActionText: String,
    private val primaryActionClick: (() -> Unit),
    private val secondaryActionText: String? = null,
    private val secondaryActionClick: (() -> Unit)? = null
) : Item() {

    override fun getLayout() = R.layout.item_error_state

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            renderTitle()
            renderSubtitle()
            renderPrimaryActionButton()
            renderSecondaryActionButton()
        }
    }

    private fun View.renderTitle() {
        item_error_state_title.text = title
    }

    private fun View.renderSubtitle() {
        item_error_state_subtitle.text = subtitle
    }

    private fun View.renderPrimaryActionButton() {
        item_error_state_primary_action_button.text = primaryActionText
        item_error_state_primary_action_button.setOnClickListener { primaryActionClick() }
    }

    private fun View.renderSecondaryActionButton() {
        item_error_state_secondary_action_button.isVisible = secondaryActionText != null
        item_error_state_secondary_action_button.text = secondaryActionText
        secondaryActionClick?.let { item_error_state_secondary_action_button.setOnClickListener { it() } }
            ?: item_error_state_secondary_action_button.setOnClickListener(null)
    }
}
