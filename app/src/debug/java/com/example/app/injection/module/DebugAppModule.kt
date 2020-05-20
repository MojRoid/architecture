package com.example.app.injection.module

import android.app.Application
import com.example.app.view.DebugApp
import com.example.base.core.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Add common application level dependencies to BaseModule, NOT here.
 */
@Module
class DebugAppModule {

    @Provides
    @PerApplication
    fun provideApplication(application: DebugApp): Application = application
}
