package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import com.yukihiro.droidkaigi2023.infra.repository.exception.AccountException
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.ErrorSnackBarStates
import java.io.IOException

object LoginErrorHandler : ErrorHandler {

    override fun handle(exception: Throwable): ErrorState {
        return when(exception) {
            is IOException -> ErrorSnackBarStates.Offline
            is AccountException -> ErrorSnackBarStates.NotLogin
            else -> ErrorSnackBarStates.Unexpected
        }
    }
}