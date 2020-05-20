package com.example.base.core.view.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <reified T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    crossinline action: (t: T) -> Unit
): Observer<T?> {
    val observer: Observer<T?> = Observer { nullable: T? -> nullable?.let { t: T -> action(t) } }
    liveData.observe(this, observer)
    return observer
}

inline fun <reified T> Fragment.observe(
    liveData: LiveData<T>,
    crossinline action: (t: T) -> Unit
): Observer<T?> {
    val observer: Observer<T?> = Observer { nullable: T? -> nullable?.let { t: T -> action(t) } }
    liveData.observe(viewLifecycleOwner, observer)
    return observer
}
