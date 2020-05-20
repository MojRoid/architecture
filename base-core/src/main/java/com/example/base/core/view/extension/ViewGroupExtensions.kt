package com.example.base.core.view.extension

import android.view.ViewGroup
import androidx.core.view.children

fun ViewGroup.removeClipping() {
    clipToPadding = false
    clipChildren = false
    children.forEach { view -> (view as? ViewGroup)?.removeClipping() }
}
