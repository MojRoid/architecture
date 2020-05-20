package com.example.base.core.view.extension

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.base.core.view.adapter.ViewHolder

inline fun RecyclerView.overScrollIfContentsScroll(crossinline onCompute: (Boolean) -> Unit = {}) {
    post {
        val lastPosition: Int = when (val lm: RecyclerView.LayoutManager? = layoutManager) {
            is LinearLayoutManager -> lm.findLastCompletelyVisibleItemPosition()
            is GridLayoutManager -> lm.findLastCompletelyVisibleItemPosition()
            is StaggeredGridLayoutManager -> lm.findLastCompletelyVisibleItemPositions(null)
                .maxBy { it } ?: 0
            else -> 0
        }

        when (lastPosition) {
            (adapter?.itemCount ?: 0) - 1 -> {
                overScrollMode = View.OVER_SCROLL_NEVER
                onCompute(false)
            }
            else -> {
                overScrollMode = View.OVER_SCROLL_ALWAYS
                onCompute(true)
            }
        }
    }
}

fun ViewHolder.setFullWidth() {
    (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
}
