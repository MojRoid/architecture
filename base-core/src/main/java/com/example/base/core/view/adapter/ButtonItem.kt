package com.example.base.core.view.adapter

import android.view.View
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_button_primary.view.*

data class ButtonItem(
    private val text: String,
    private val id: Long = text.hashCode().toLong(),
    private val isEnabled: Boolean = true,
    private val style: Style = Style.PRIMARY,
    private val onClick: (() -> Unit)? = null
) : Item() {

    enum class Style {
        PRIMARY,
        SECONDARY,
        TERTIARY,
    }

    override fun getId(): Long = id

    override fun getLayout() = when (style) {
        Style.PRIMARY -> R.layout.item_button_primary
        Style.SECONDARY -> R.layout.item_button_secondary
        Style.TERTIARY -> R.layout.item_button_tertiary
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            renderText()
            renderEnabledState()
            renderOnClickListener()
        }
    }

    private fun View.renderText() {
        item_button.text = text
    }

    private fun View.renderEnabledState() {
        item_button.isEnabled = this@ButtonItem.isEnabled
    }

    private fun View.renderOnClickListener() {
        when (onClick) {
            null -> item_button.setOnClickListener(null)
            else -> item_button.setOnClickListener { onClick.invoke() }
        }
    }
}
