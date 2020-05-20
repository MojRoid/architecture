package com.example.base.test.usecase

import androidx.lifecycle.MutableLiveData
import com.example.base.core.usecase.BaseUseCase
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import kotlin.reflect.KClass

class TestUseCase<T : BaseUseCase<RESULT, ARGUMENT>, RESULT, ARGUMENT>(
    private val useCaseClass: KClass<T>
) {

    lateinit var useCase: T
    lateinit var result: MutableLiveData<RESULT>

    fun setup() {
        useCase = mock(useCaseClass.java)
        result = MutableLiveData()
        given(useCase.result).willReturn(result)
    }
}
