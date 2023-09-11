package com.yukihiro.droidkaigi2023.ui.error.dialog

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState

abstract class CommonErrorDialogStates(@StringRes matter: Int) {

    val Offline = ErrorDialogState(
            matter = matter,
            reason = R.string.error_dialog_reason_offline
        )

    val LoadFail = ErrorDialogState(
        matter = matter,
        reason = R.string.error_dialog_reason_load_fail
    )
}