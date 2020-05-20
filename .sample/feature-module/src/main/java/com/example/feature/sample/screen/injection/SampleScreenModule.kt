package com.example.feature.sample.screen.injection

import androidx.lifecycle.ViewModelProvider
import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.sample.screen.view.SampleScreenFragment
import com.example.feature.sample.screen.viewmodel.SampleScreenViewModel
import dagger.Module
import dagger.Provides

@Module
class SampleScreenModule {

    @Provides
    @PerFragment
    fun provideViewModel(
        fragment: SampleScreenFragment,
        factory: SampleScreenViewModel.Factory
    ): SampleScreenViewModel = ViewModelProvider(fragment, factory)
        .get(SampleScreenViewModel::class.java)
}
