package com.example.base.core.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter

class GroupAdapterItemOffsetsDecoration(
    private val decoration: (GroupAdapter<*>, Int, View) -> Unit
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter: GroupAdapter<*> = parent.adapter as? GroupAdapter<*> ?: return
        val position: Int = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        decoration(adapter, position, view)
    }
}
