package com.example.base.core.view.extension

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorInt


fun CharSequence.setColorSpans(
    @ColorInt color: Int, vararg subtexts: String
): SpannableString = SpannableString(this).apply {
    subtexts.forEach { subtext ->
        val index = this.indexOf(subtext)
        setSpan(
            ForegroundColorSpan(color),
            index,
            index + subtext.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

fun CharSequence.setClickSpan(
    subtext: String,
    onClick: (View) -> Unit
): SpannableString = SpannableString(this).apply {
    val index = this.indexOf(subtext)
    setSpan(
        object : ClickableSpan() {
            override fun onClick(widget: View) {
                onClick(widget)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }, index,
        index + subtext.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}
