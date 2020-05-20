package com.example.feature.sample.screen.injection

import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.sample.screen.view.ExampleListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExampleListBindings {

    @PerFragment
    @ContributesAndroidInjector(modules = [ExampleListModule::class])
    abstract fun bindExampleListFragment(): ExampleListFragment
}
