package com.example.base.core.view.adapter

import android.view.View
import com.xwray.groupie.Item

abstract class Item : Item<ViewHolder>() {

    override fun createViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
}
