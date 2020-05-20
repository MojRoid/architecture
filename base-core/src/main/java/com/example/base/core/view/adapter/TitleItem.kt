package com.example.base.core.view.adapter

import android.view.View
import androidx.core.view.isVisible
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_title.view.*

data class TitleItem(
    val title: String? = null
) : Item() {

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = R.layout.item_title

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            renderName()
        }
    }

    private fun View.renderName() {
        item_title.isVisible = title != null
        item_title.text = title
    }
}
