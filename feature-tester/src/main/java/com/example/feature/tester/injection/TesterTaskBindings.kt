package com.example.feature.tester.injection

import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.tester.view.TesterFragment
import com.example.feature.tester.view.TypographyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TesterTaskBindings {

    @PerFragment
    @ContributesAndroidInjector(modules = [TesterModule::class])
    abstract fun bindTesterFragment(): TesterFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindTypographyFragment(): TypographyFragment
}
