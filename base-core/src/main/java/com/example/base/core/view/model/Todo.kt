package com.example.base.core.view.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Todo(
    val id: Long,
    val userId: Long,
    val title: String,
    val completed: Boolean
) : Parcelable
