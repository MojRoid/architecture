package com.example.feature.sample.screen.injection

import androidx.lifecycle.ViewModelProvider
import com.example.base.core.injection.scopes.PerFragment
import com.example.feature.sample.screen.view.ExampleListFragment
import com.example.feature.sample.screen.viewmodel.ExampleListViewModel
import dagger.Module
import dagger.Provides

@Module
class ExampleListModule {

    @Provides
    @PerFragment
    fun provideViewModel(
        fragment: ExampleListFragment,
        factory: ExampleListViewModel.Factory
    ): ExampleListViewModel = ViewModelProvider(fragment, factory)
        .get(ExampleListViewModel::class.java)
}
