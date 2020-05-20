package com.example.app.view

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import com.example.app.injection.component.DaggerAppComponent
import com.google.android.material.resources.TextAppearanceConfig
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.Dispatchers

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        setNightMode()

        // Slow init of this causes a disk read violation
        // Temporary workaround until I can look into it some more
        // See https://github.com/Kotlin/kotlinx.coroutines/issues/878
        Dispatchers.Main
        AndroidThreeTen.init(this)
        TextAppearanceConfig.setShouldLoadFontSynchronously(true)
    }

    private fun setNightMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
