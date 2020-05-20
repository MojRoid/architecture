package com.example.base.core.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.*
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var dispatcher: CoroutineDispatcher

    private val job: Job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutResourceId, container, false)

    override fun onDestroy() {
        job.cancelChildren()
        super.onDestroy()
    }

    /**
     * Return true if this action is consumed or false to perform default back pressed behaviour.
     */
    open fun onBackPressed(): Boolean = false
}
