package com.example.base.core.view.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri


fun Activity.launchEmail(email: String, subject: String, body: String) {
    Intent(Intent.ACTION_SENDTO).apply {
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        data = Uri.fromParts("mailto", email, null)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }.run { startActivity(this) }
}

fun Activity.launchPhone(phone: String) {
    Intent(Intent.ACTION_DIAL).apply {
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        data = Uri.fromParts("tel", phone, null)
    }.run { startActivity(this) }
}

fun Activity.launchUrl(url: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }.run { startActivity(this) }
}