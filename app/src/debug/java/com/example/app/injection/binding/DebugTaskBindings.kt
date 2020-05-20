package com.example.app.injection.binding

import com.example.base.core.injection.scopes.PerActivity
import com.example.feature.tester.injection.TesterTaskModule
import com.example.feature.tester.view.TesterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Add debug specific task bindings here
 */
@Module
abstract class DebugTaskBindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [TesterTaskModule::class])
    abstract fun bindTesterActivity(): TesterActivity
}
