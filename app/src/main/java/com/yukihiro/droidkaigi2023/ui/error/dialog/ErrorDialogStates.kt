package com.yukihiro.droidkaigi2023.ui.error.dialog

import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState

object ErrorDialogStates {
    val LoginFailOffline = ErrorDialogState(
        matter = R.string.error_dialog_matter_login,
        reason = R.string.error_dialog_reason_offline
    )
}