package com.example.base.core.view.extension

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener

fun Activity.checkPermission(permission: String, onGranted: () -> Unit, onDenied: () -> Unit = {}) {
    Dexter.withActivity(this)
        .withPermission(permission)
        .withListener(object : BasePermissionListener() {

            override fun onPermissionGranted(response: PermissionGrantedResponse) {
                onGranted()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse) {
                onDenied()
            }
        })
        .check()
}

fun Activity.isPermissionGranted(permission: String): Boolean = ContextCompat
    .checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
