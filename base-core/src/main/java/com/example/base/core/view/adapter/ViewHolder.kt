package com.example.base.core.view.adapter

import android.view.View
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.extensions.LayoutContainer

@ContainerOptions(cache = CacheImplementation.HASH_MAP)
open class ViewHolder(
    override val containerView: View
) : GroupieViewHolder(containerView), LayoutContainer
