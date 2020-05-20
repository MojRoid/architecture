package com.example.app.injection.module

import android.app.Application
import com.example.app.view.App
import com.example.app.view.navigator.TaskNavigatorImpl
import com.example.base.core.injection.scopes.PerApplication
import com.example.base.core.view.navigator.TaskNavigator
import dagger.Module
import dagger.Provides

/**
 * Add common application level dependencies to BaseModule, NOT here.
 */
@Module
class AppModule {

    @Provides
    @PerApplication
    fun provideApplication(application: App): Application = application

    @Provides
    @PerApplication
    fun provideNavigator(navigator: TaskNavigatorImpl): TaskNavigator = navigator
}
