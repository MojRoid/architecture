package com.example.base.core.view.adapter

import com.example.base.core.R

object LoadingStateItem : Item() {

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = R.layout.item_loading_state

    override fun bind(viewHolder: ViewHolder, position: Int) {}
}
