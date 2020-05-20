package com.example.base.core.view.adapter

import android.view.View
import androidx.annotation.DimenRes
import com.example.base.core.R
import com.example.base.core.view.extension.getDimensionPixel
import com.example.base.core.view.extension.setMargin
import kotlinx.android.synthetic.main.item_space_surface.view.*

data class SpaceItem(
    @DimenRes private val spaceDimenRes: Int = R.dimen.space_regular,
    private val style: Style = Style.SURFACE
) : Item() {

    enum class Style {
        SURFACE,
        BACKGROUND
    }

    override fun getId(): Long = hashCode().toLong()

    override fun getLayout() = when (style) {
        Style.SURFACE -> R.layout.item_space_surface
        Style.BACKGROUND -> R.layout.item_space_background
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.renderSpace()

    }

    private fun View.renderSpace() {
        item_space_view.setMargin(bottom = context.getDimensionPixel(spaceDimenRes))
    }
}
