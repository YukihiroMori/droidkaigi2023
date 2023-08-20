package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.NoError

object LoginErrorHandler : ErrorHandler {

    override fun handle(exception: Throwable): ErrorState {
        Firebase.crashlytics.recordException(exception)
        return NoError
    }
}