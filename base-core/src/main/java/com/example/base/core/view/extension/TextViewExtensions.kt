package com.example.base.core.view.extension

import android.text.StaticLayout
import android.widget.TextView

fun TextView.calculateHeight(width: Int, text: String): Int = StaticLayout.Builder
    .obtain(text, 0, text.length, paint, width)
    .setBreakStrategy(breakStrategy)
    .setHyphenationFrequency(hyphenationFrequency)
    .setIncludePad(includeFontPadding)
    .setEllipsize(ellipsize)
    .setMaxLines(maxLines)
    .build()
    .height
