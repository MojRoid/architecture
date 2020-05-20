package com.example.base.core.view.adapter

import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_header.view.*

data class HeaderItem(
    val title: String? = null,
    private val subtitle: String? = null,
    @DrawableRes private val iconResourceId: Int? = null,
    private val iconTint: ColorStateList? = null
) : Item() {

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = R.layout.item_header

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            renderName()
            renderSubtitle()
            renderIcon()
        }
    }

    private fun View.renderName() {
        item_header_title.isVisible = title != null
        item_header_title.text = title
    }

    private fun View.renderSubtitle() {
        item_header_subtitle.isVisible = subtitle != null
        item_header_subtitle.text = subtitle
    }

    private fun View.renderIcon() {
        item_header_icon.isVisible = iconResourceId != null
        iconResourceId?.let { item_header_icon.setImageResource(it) }
        iconTint?.let { item_header_icon.imageTintList = iconTint }
    }
}
