package com.example.base.core.view.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes

fun View.toBitmap(): Bitmap {
    val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    bitmap.eraseColor(Color.TRANSPARENT)

    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

fun Bitmap.toDrawable(resources: Resources): Drawable = BitmapDrawable(resources, this)

fun View.toDrawable(resources: Resources): Drawable = toBitmap().toDrawable(resources)

fun Context.toBitmap(@DrawableRes drawableResId: Int): Bitmap? {
    val drawable: Drawable = getDrawable(drawableResId) ?: return null
    val bitmap: Bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
