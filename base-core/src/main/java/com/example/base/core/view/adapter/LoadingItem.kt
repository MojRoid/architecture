package com.example.base.core.view.adapter

import android.view.View
import androidx.core.view.isVisible
import com.example.base.core.R
import kotlinx.android.synthetic.main.item_loading.view.*

data class LoadingItem(
    val message: String? = null
) : Item() {

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = R.layout.item_loading

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.renderMessage()
    }

    private fun View.renderMessage() {
        item_loading_message.apply {
            text = message
            isVisible = message != null
        }
    }
}
