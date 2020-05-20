package com.example.feature.tester.injection

import android.content.Context
import com.example.base.core.injection.scopes.PerActivity
import com.example.feature.tester.view.TesterActivity
import dagger.Module
import dagger.Provides

@Module(includes = [TesterTaskBindings::class])
class TesterTaskModule {

    @Provides
    @PerActivity
    fun provideContext(activity: TesterActivity): Context = activity
}
