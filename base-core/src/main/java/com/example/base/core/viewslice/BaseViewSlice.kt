package com.example.base.core.viewslice

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.*
import com.example.base.core.view.base.BaseActivity
import com.example.base.core.view.base.BaseFragment
import com.example.base.core.view.extension.getContentView
import com.example.base.core.view.extension.observe
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewSlice<RESULT>(
    protected val initialResult: RESULT
) : LifecycleObserver {

    protected lateinit var scope: CoroutineScope
    protected lateinit var lifecycleOwner: LifecycleOwner
    protected lateinit var lifecycle: Lifecycle
    protected lateinit var resources: Resources
    protected lateinit var view: View
    protected lateinit var context: Context
    protected lateinit var activity: BaseActivity

    protected val state: MediatorLiveData<RESULT> = MediatorLiveData()
    val result: LiveData<RESULT> get() = state
    val resultValue: RESULT get() = result.value ?: initialResult

    fun init(activity: BaseActivity) {
        scope = activity.scope
        lifecycleOwner = activity
        lifecycle = lifecycleOwner.lifecycle
        lifecycle.addObserver(this)
        resources = activity.resources
        view = activity.getContentView()
        context = activity
        this.activity = activity
    }

    fun init(fragment: BaseFragment) {
        scope = fragment.scope
        lifecycleOwner = fragment.viewLifecycleOwner
        lifecycle = lifecycleOwner.lifecycle
        lifecycle.addObserver(this)
        resources = fragment.resources
        view = fragment.requireView()
        context = fragment.requireContext()
        activity = fragment.requireActivity() as BaseActivity
    }

    protected fun setResult(result: RESULT) {
        state.value = result
    }

    protected inline fun updateResult(result: RESULT.() -> RESULT) {
        setResult(result(state.value ?: initialResult))
    }

    protected inline fun launch(
        crossinline onError: (Throwable) -> Unit = { it.printStackTrace() },
        crossinline block: suspend CoroutineScope.() -> Unit
    ): Job = scope.launch(CoroutineExceptionHandler { _, throwable -> onError(throwable) }) {
        block()
    }

    protected fun <T : View> find(@IdRes id: Int): T = view.findViewById(id)

    protected inline fun <reified T> observe(
        liveData: LiveData<T>,
        crossinline action: (t: T) -> Unit
    ) {
        lifecycleOwner.observe(liveData, action)
    }
}
