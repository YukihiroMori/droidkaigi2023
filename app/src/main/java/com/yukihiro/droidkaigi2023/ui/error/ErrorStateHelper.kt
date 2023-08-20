package com.yukihiro.droidkaigi2023.ui.error

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorSectionState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

class ErrorStateHelper(
    private val errorHandler: ErrorHandler
) {
    private val _errorStateFlow = MutableStateFlow(ErrorSectionState.default())
    val errorStateFlow: StateFlow<ErrorSectionState> = _errorStateFlow.asStateFlow()

    fun handleError(exception: Throwable) {
        Firebase.crashlytics.recordException(exception)
        _errorStateFlow.updateAndGet { state ->
            state.copy(
                errorList = state.errorList.toMutableList().also {
                    it.add(errorHandler.handle(exception))
                }
            )
        }
    }

    fun <T: ErrorState> consumeErrorState(state: T){
        _errorStateFlow.updateAndGet { errorState ->
            errorState.copy(
                errorList = errorState.errorList.filter { it != state as ErrorState }
            )
        }
    }
}