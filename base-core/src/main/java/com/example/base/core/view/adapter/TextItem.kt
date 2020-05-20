package com.example.base.core.view.adapter

import android.view.View
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_text.view.*

data class TextItem(
    private val text: String,
    private val id: Long? = null
) : Item() {

    override fun getId(): Long = id ?: text.hashCode().toLong()

    override fun getLayout() = R.layout.item_text

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.renderText()
    }

    private fun View.renderText() {
        item_text_view.text = text
    }
}
