package com.yukihiro.droidkaigi2023.ui.error

import com.yukihiro.droidkaigi2023.ui.error.compose.listener.ErrorSectionListener
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorSectionState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

class ErrorStateHelper(
    private val errorHandler: ErrorHandler,
    private val errorListener: ErrorListener?,
) : ErrorSectionListener {
    private val _errorSectionStateFlow = MutableStateFlow(ErrorSectionState.default())
    val errorSectionStateFlow: StateFlow<ErrorSectionState> = _errorSectionStateFlow.asStateFlow()

    fun handleException(exception: Throwable) {
        val state = errorHandler.handle(exception)
        _errorSectionStateFlow.updateAndGet { errorState ->
            errorState.copy(
                errorSet = errorState.errorSet.plus(state)
            )
        }
    }

    private fun <T : ErrorState> consumeErrorState(state: T) {
        _errorSectionStateFlow.updateAndGet { errorState ->
            errorState.copy(
                errorSet = errorState.errorSet.minus(state)
            )
        }
    }

    override fun onClickErrorDialogConfirm(state: ErrorDialogState) {
        errorListener?.onClickErrorDialogConfirm(state)
        consumeErrorState(state)
    }

    override fun onClickErrorDialogDismiss(state: ErrorDialogState) {
        errorListener?.onClickErrorDialogDismiss(state)
        consumeErrorState(state)
    }

    override fun onErrorSnackBarDismiss(state: ErrorSnackBarState) {
        errorListener?.onErrorSnackBarDismiss(state)
        consumeErrorState(state)
    }
}