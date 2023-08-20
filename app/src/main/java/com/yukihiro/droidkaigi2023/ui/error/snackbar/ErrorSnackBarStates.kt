package com.yukihiro.droidkaigi2023.ui.error.snackbar

import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState

object ErrorSnackBarStates {
    val Offline = ErrorSnackBarState(
        message = R.string.error_snack_bar_message_offline
    )
    val NotLogin = ErrorSnackBarState(
        message = R.string.error_snack_bar_message_not_login
    )
    val Unexpected = ErrorSnackBarState(
        message = R.string.error_snack_bar_message_unexpected
    )
}