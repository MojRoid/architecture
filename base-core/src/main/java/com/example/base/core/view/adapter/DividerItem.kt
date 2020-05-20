package com.example.base.core.view.adapter

import com.example.base.core.R

object DividerItem : Item() {

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = R.layout.item_divider

    override fun bind(viewHolder: ViewHolder, position: Int) {}
}
