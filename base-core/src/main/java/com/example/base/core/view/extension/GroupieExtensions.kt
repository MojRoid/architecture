package com.example.base.core.view.extension

import com.xwray.groupie.GroupAdapter
import com.example.base.core.view.adapter.Item


inline fun <reified T : Item> GroupAdapter<*>.isItemOfType(position: Int): Boolean =
    getItem(position) is T

inline fun <reified T : Item> GroupAdapter<*>.isPreviousItemOfType(position: Int): Boolean =
    position != 0 && isItemOfType<T>(position - 1)

inline fun <reified T : Item> GroupAdapter<*>.isNextItemOfType(position: Int): Boolean =
    position != itemCount - 1 && isItemOfType<T>(position + 1)
