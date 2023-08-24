package com.yukihiro.droidkaigi2023.ui.error

import androidx.annotation.StringRes
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yukihiro.droidkaigi2023.ui.error.compose.listener.ErrorSectionListener
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorSectionState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

class ErrorStateHelper(
    private val errorHandler: ErrorHandler,
    private val errorListener: ErrorListener?,
): ErrorSectionListener {
    private val _errorStateFlow = MutableStateFlow(ErrorSectionState.default())
    val errorStateFlow: StateFlow<ErrorSectionState> = _errorStateFlow.asStateFlow()

    fun handleError(
        @StringRes matter: Int,
        exception: Throwable
    ) {
        val errorState = errorHandler.handle(matter, exception)
        _errorStateFlow.updateAndGet { state ->
            state.copy(
                errorList = state.errorList.toMutableList().also {
                    it.add(errorState)
                }.distinct()
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

    override fun onNavigatedMaintenance(state: MaintenanceState) {
        errorListener?.onNavigatedMaintenance(state)
        consumeErrorState(state)
    }

    private fun <T : ErrorState> consumeErrorState(state: T) {
        _errorStateFlow.updateAndGet { errorState ->
            errorState.copy(
                errorList = errorState.errorList
                    .filter { it != state as ErrorState }
            )
        }
    }
}