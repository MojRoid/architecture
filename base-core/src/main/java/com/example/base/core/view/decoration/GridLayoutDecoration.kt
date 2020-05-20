package com.example.base.core.view.decoration

import android.content.Context
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.base.core.R
import com.example.base.core.view.extension.getDimensionPixel
import com.example.base.core.view.extension.setPadding

class GridLayoutDecoration(context: Context) {

    private val marginSmall: Int = context.getDimensionPixel(R.dimen.space_small)
    private val marginRegular: Int = context.getDimensionPixel(R.dimen.space_regular)

    fun create(spanCount: Int) = GroupAdapterItemOffsetsDecoration { _, _, view ->
        val column: Int = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex

        when {
            column == 0 -> view.setPadding(
                top = marginRegular,
                left = marginRegular,
                right = marginSmall
            )
            column < spanCount - 1 -> view.setPadding(
                top = marginRegular,
                left = marginSmall,
                right = marginSmall
            )
            column == spanCount - 1 -> view.setPadding(
                top = marginRegular,
                left = marginSmall,
                right = marginRegular
            )
        }
    }
}
