package com.example.feature.tester.injection

import androidx.lifecycle.ViewModelProvider
import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.tester.view.TesterFragment
import com.example.feature.tester.viewmodel.TesterViewModel
import dagger.Module
import dagger.Provides

@Module
class TesterModule {

    @Provides
    @PerFragment
    fun provideViewModel(
        fragment: TesterFragment,
        factory: TesterViewModel.Factory
    ): TesterViewModel = ViewModelProvider(fragment, factory)
        .get(TesterViewModel::class.java)
}
