package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.ErrorSnackBarStates
import java.net.SocketException

object LoginErrorHandler : ErrorHandler {

    override fun handle(
        @StringRes matter: Int,
        exception: Throwable
    ): ErrorState {
        return when(exception) {
            is SocketException -> ErrorSnackBarStates.Offline
            else -> ErrorSnackBarStates.Unexpected
        }
    }
}