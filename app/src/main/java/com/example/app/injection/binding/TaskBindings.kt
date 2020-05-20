package com.example.app.injection.binding

import com.example.base.core.injection.scopes.PerActivity
import com.example.feature.main.injection.MainTaskModule
import com.example.feature.main.view.MainActivity
import com.example.feature.main.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TaskBindings {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainTaskModule::class])
    abstract fun bindMainActivity(): MainActivity
}
