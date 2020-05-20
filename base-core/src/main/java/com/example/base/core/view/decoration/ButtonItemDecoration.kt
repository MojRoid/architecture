package com.example.base.core.view.decoration

import android.content.Context
import com.example.base.core.R
import com.example.base.core.view.adapter.ButtonItem
import com.example.base.core.view.extension.*

class ButtonItemDecoration(context: Context) {

    private val marginRegular: Int = context.getDimensionPixel(R.dimen.space_regular)
    private val marginLarge: Int = context.getDimensionPixel(R.dimen.space_large)

    fun create() = GroupAdapterItemOffsetsDecoration { adapter, position, view ->
        if (!adapter.isItemOfType<ButtonItem>(position)) return@GroupAdapterItemOffsetsDecoration

        when {
            adapter.isPreviousItemOfType<ButtonItem>(position) &&
                    adapter.isNextItemOfType<ButtonItem>(position) -> view.setPadding(
                left = marginLarge,
                top = 0,
                right = marginLarge,
                bottom = 0
            )
            adapter.isNextItemOfType<ButtonItem>(position) -> view.setPadding(
                left = marginLarge,
                top = marginRegular,
                right = marginLarge,
                bottom = 0
            )
            adapter.isPreviousItemOfType<ButtonItem>(position) -> view.setPadding(
                left = marginLarge,
                top = 0,
                right = marginLarge,
                bottom = marginRegular
            )
        }
    }
}
