package com.example.feature.sample.screen.viewmodel

import com.example.base.core.viewmodel.base.BaseViewModel
import javax.inject.Inject

class SampleScreenViewModel(
) : BaseViewModel<SampleScreenAction, SampleScreenState>(SampleScreenState()) {

    class Factory @Inject constructor(
    ) : BaseViewModel.Factory(
        SampleScreenViewModel(
        )
    )
}
