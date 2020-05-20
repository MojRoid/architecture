package com.example.base.core.view.adapter

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_row.view.*

data class RowItem(
    private val title: String? = null,
    private val description: String? = null,
    @DrawableRes private val icon: Int? = null,
    private val buttonOnClick: ButtonAction? = null,
    private val onItemClick: (() -> Unit)? = null
) : Item() {

    data class ButtonAction(val name: String, val action: () -> Unit)

    override fun getId(): Long = title.hashCode().toLong() + description.hashCode().toLong()

    override fun getLayout() = R.layout.item_row

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            renderTitle()
            renderDescription()
            renderIcon()
            renderButtonOnClick()
            renderOnItemClick()
        }
    }

    private fun View.renderTitle() {
        item_row_title.apply {
            text = title
            isVisible = title != null
        }
    }

    private fun View.renderDescription() {
        item_row_description.apply {
            text = description
            isVisible = description != null
        }
    }

    private fun View.renderIcon() {
        item_row_icon.apply {
            if (icon != null) setImageResource(icon)
            isVisible = icon != null
        }
    }

    private fun View.renderButtonOnClick() {
        item_row_button.apply {
            if (buttonOnClick != null) {
                text = buttonOnClick.name
                setOnClickListener { buttonOnClick.action() }
            }
            isVisible = buttonOnClick != null
        }
    }

    private fun View.renderOnItemClick() {
        when {
            onItemClick != null -> setOnClickListener { onItemClick.invoke() }
            else -> setOnClickListener(null)
        }
    }
}
