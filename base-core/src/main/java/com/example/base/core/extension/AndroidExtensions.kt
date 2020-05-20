package com.example.base.core.extension

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import androidx.fragment.app.Fragment
import timber.log.Timber
import kotlin.math.pow
import kotlin.math.sqrt


fun runOnMainThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post { action() }
}

private const val MINIMUM_TABLET_SIZE_INCHES = 7

fun Activity.isTablet(): Boolean {

    val windowManager: WindowManager = windowManager
    val display: Display = windowManager.defaultDisplay

    val displayMetrics = DisplayMetrics()
    display.getMetrics(displayMetrics)

    val realSize = Point()
    display.getRealSize(realSize)

    val widthPixels: Int = realSize.x
    val heightPixels: Int = realSize.y

    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)

    val x: Double = (widthPixels / dm.xdpi.toDouble()).pow(2.0)
    val y: Double = (heightPixels / dm.ydpi.toDouble()).pow(2.0)
    val inches = sqrt(x + y)

    Timber.i("SCREEN SIZE IN INCHES: $inches")
    return inches >= MINIMUM_TABLET_SIZE_INCHES
}

fun Fragment.isPortrait(): Boolean =
    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
