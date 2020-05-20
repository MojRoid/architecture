package com.example.app.view

import android.os.StrictMode
import com.example.app.injection.component.DaggerDebugAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

open class DebugApp : App() {

    @Inject
    lateinit var loggingTree: Timber.Tree

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerDebugAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        setupDebugTools()
    }

    private fun setupDebugTools() {
        initTimber()
        initStetho()
        initStrictMode()
    }

    private fun initTimber() {
        Timber.plant(loggingTree)
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}
