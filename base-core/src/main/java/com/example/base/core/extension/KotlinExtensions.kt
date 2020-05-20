package com.example.base.core.extension

import timber.log.Timber

inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) {
        block()
    }
}

fun Throwable.log() {
    Timber.e(this)
//    FirebaseCrashlytics.getInstance().recordException(this)
}

fun <T : Any> T.accessField(fieldName: String): Any? {
    return javaClass.getDeclaredField(fieldName).let { field ->
        field.isAccessible = true
        return@let field.get(this)
    }
}
