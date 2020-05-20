package com.example.base.core.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.base.core.view.extension.keepScreenOn
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var dispatcher: CoroutineDispatcher

    private val job: Job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Main + job)

    @get:LayoutRes
    protected abstract val layoutResourceId: Int?

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        layoutResourceId?.let { setContentView(it) }
        keepScreenOn()
    }

    override fun onDestroy() {
        job.cancelChildren()
        super.onDestroy()
    }

    override fun onBackPressed() {
        val consumed: Boolean =
            ((supportFragmentManager.fragments.firstOrNull() as? NavHostFragment)
                ?.childFragmentManager?.fragments?.firstOrNull() as? BaseFragment)?.onBackPressed()
                ?: false

        if (!consumed) super.onBackPressed()
    }
}
