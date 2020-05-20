package com.example.base.core.view.extension

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup


fun Float.dpToPixels(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.spToPixels(): Int = TypedValue
    .applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)
    .toInt()

fun Float.pixelsToDp(): Float = this / Resources.getSystem().displayMetrics
    .densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT

fun screenWidth(): Int = Resources.getSystem().displayMetrics.widthPixels

fun screenHeight(): Int = Resources.getSystem().displayMetrics.heightPixels

fun View.setMargin(all: Int) {
    setMargin(all, all, all, all)
}

fun View.removeParents() {
    (parent as? ViewGroup)?.let { viewGroup ->
        viewGroup.removeView(this)
        viewGroup.removeParents()
    }
}

fun View.setMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    val layoutParams: ViewGroup.MarginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams

    val leftMargin: Int = left ?: layoutParams.leftMargin
    val topMargin: Int = top ?: layoutParams.topMargin
    val rightMargin: Int = right ?: layoutParams.rightMargin
    val bottomMargin: Int = bottom ?: layoutParams.bottomMargin

    layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
    setLayoutParams(layoutParams)
}

fun View.setPadding(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    val leftPadding: Int = left ?: paddingLeft
    val topPadding: Int = top ?: paddingTop
    val rightPadding: Int = right ?: paddingRight
    val bottomPadding: Int = bottom ?: paddingBottom

    setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
}
