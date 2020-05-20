package com.example.base.core.view.extension

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun Fragment.isCurrentDestination(@IdRes id: Int): Boolean =
    findNavController().currentDestination?.id == id

fun Activity.isCurrentDestination(navHost: Fragment, @IdRes id: Int): Boolean =
    NavHostFragment.findNavController(navHost).currentDestination?.id == id

fun Fragment.setResult(apply: SavedStateHandle.() -> Unit) {
    findNavController().previousBackStackEntry?.savedStateHandle?.apply()
}

fun <T> Fragment.getResult(key: String): T? =
    findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)

fun Fragment.nullifyResult(key: String) {
    findNavController().currentBackStackEntry?.savedStateHandle?.set(key, null)
}