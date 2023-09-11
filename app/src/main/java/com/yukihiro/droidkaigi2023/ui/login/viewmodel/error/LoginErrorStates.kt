package com.yukihiro.droidkaigi2023.ui.login.viewmodel.error

import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState

object LoginErrorStates{
    object SnackBar {
        val Offline = ErrorSnackBarState(
            message = R.string.error_snack_bar_message_offline
        )
        val ServerError = ErrorSnackBarState(
            message = R.string.error_snack_bar_message_server_error
        )
        val Unexpected = ErrorSnackBarState(
            message = R.string.error_snack_bar_message_unexpected
        )
    }
}