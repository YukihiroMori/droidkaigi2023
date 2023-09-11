package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

object LoginErrorHandler : ErrorHandler {
    override fun handle(exception: Throwable): ErrorState {

        Firebase.crashlytics.recordException(exception)
        return when (exception) {
            is AccountException.InternetException
                -> LoginErrorStates.SnackBar.Offline
            is AccountException.ServerError
                -> LoginErrorStates.SnackBar.ServerError

            else -> LoginErrorStates.SnackBar.Unexpected
        }
    }
}