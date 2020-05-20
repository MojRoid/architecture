package com.example.app.view.navigator

import android.content.Context
import android.content.Intent
import com.example.base.core.view.base.BaseActivity
import com.example.base.core.view.navigator.TaskNavigator
import javax.inject.Inject
import kotlin.reflect.KClass

class TaskNavigatorImpl @Inject constructor() : TaskNavigator {

    override fun toDebugger(context: Context) {
//        context.start(DebuggerActivity::class)
    }

    private fun Context.start(
        kClass: KClass<out BaseActivity>,
        applyExtras: Intent.() -> Unit = {}
    ) {
        Intent(this, kClass.java).run {
            applyExtras()
            startActivity(this)
        }
    }
}
