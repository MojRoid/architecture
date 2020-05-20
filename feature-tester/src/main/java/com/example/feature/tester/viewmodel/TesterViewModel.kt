package com.example.feature.tester.viewmodel

import com.example.base.core.viewmodel.base.BaseViewModel
import javax.inject.Inject

class TesterViewModel(
) : BaseViewModel<TesterAction, TesterState>(TesterState()) {

    class Factory @Inject constructor(
    ) : BaseViewModel.Factory(
        TesterViewModel(
        )
    )
}
