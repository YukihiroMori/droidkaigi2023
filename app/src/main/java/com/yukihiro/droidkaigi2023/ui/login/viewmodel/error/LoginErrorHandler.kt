package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.domain.repository.account.exception.AccountException
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.dialog.ErrorDialogStatesCreator
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.ErrorSnackBarStates
import java.io.IOException

object LoginErrorHandler : ErrorHandler {

    override fun handle(
        @StringRes matter: Int,
        exception: Throwable
    ): ErrorState {
        return when(exception) {
            is AccountException -> ErrorSnackBarStates.NotLogin
            is IOException -> ErrorSnackBarStates.Offline
            else -> ErrorSnackBarStates.Unexpected
        }
    }
}