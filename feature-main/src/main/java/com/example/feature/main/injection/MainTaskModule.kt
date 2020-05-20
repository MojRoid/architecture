package com.example.feature.main.injection

import android.app.Activity
import android.content.Context
import com.example.base.core.injection.scopes.PerActivity
import com.example.feature.main.view.MainActivity
import dagger.Module
import dagger.Provides

@Module(includes = [MainTaskBindings::class])
class MainTaskModule {

    @Provides
    @PerActivity
    fun provideContext(activity: MainActivity): Context = activity

    @Provides
    @PerActivity
    fun provideActivity(activity: MainActivity): Activity = activity
}
