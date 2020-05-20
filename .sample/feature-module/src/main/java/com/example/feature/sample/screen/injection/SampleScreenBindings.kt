package com.example.feature.sample.screen.injection

import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.sample.screen.view.SampleScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SampleScreenBindings {

    @PerFragment
    @ContributesAndroidInjector(modules = [SampleScreenModule::class])
    abstract fun bindSampleScreenFragment(): SampleScreenFragment
}
