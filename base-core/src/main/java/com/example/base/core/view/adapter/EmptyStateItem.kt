package com.example.base.core.view.adapter

import android.view.View
import androidx.core.view.isVisible
import com.example.base.core.R
import com.example.base.core.view.extension.setFullWidth
import kotlinx.android.synthetic.main.item_empty_state.view.*

data class EmptyStateItem(
    private val title: String,
    private val subtitle: CharSequence,
    private val buttonText: String? = null,
    private val onButtonClick: (() -> Unit)? = null
) : Item() {

    override fun getLayout() = R.layout.item_empty_state

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.setFullWidth()
        viewHolder.itemView.apply {
            renderTitle()
            renderSubtitle()
            renderButton()
        }
    }

    private fun View.renderTitle() {
        item_empty_state_title.text = title
    }

    private fun View.renderSubtitle() {
        item_empty_state_subtitle.text = subtitle
    }

    private fun View.renderButton() {
        item_empty_state_button.isVisible = buttonText != null
        item_empty_state_button.text = buttonText
        onButtonClick?.let { item_empty_state_button.setOnClickListener { it() } }
            ?: item_empty_state_button.setOnClickListener(null)
    }
}
