package com.example.base.core.view.extension

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AnyRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.core.graphics.ColorUtils

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.getDrawableFromAttribute(@AttrRes attribute: Int): Drawable? =
    getDrawable(getAttributeResourceId(attribute))

fun Context.getDimensionPixel(@DimenRes resourceId: Int): Int =
    resources.getDimension(resourceId).toInt()

@AnyRes
fun Context.getAttributeResourceId(@AttrRes attribute: Int): Int {
    val outValue = TypedValue()
    theme.resolveAttribute(attribute, outValue, true)
    return outValue.resourceId
}

@ColorInt
fun Context.getColorFromAttribute(@AttrRes attribute: Int): Int =
    getColor(getAttributeResourceId(attribute))

fun Context.getColorStateListFromAttribute(@AttrRes attribute: Int): ColorStateList =
    getColorStateList(getAttributeResourceId(attribute))

@ColorInt
fun @receiver:ColorInt Int.withAlpha(alpha: Float): Int = ColorUtils.setAlphaComponent(this, (255 * alpha).toInt())

fun Context.vibrate(duration: Long = 20) {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(duration)
    }
}
