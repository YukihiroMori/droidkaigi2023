package com.yukihiro.droidkaigi2023.ui.error.dialog

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState

object ErrorDialogStatesCreator {

    fun createOffline(@StringRes matter: Int): ErrorDialogState =
        ErrorDialogState(
            matter = matter,
            reason = R.string.error_dialog_reason_offline
        )

    fun createLoadFail(@StringRes matter: Int): ErrorDialogState =
        ErrorDialogState(
            matter = matter,
            reason = R.string.error_dialog_reason_load_fail
        )
}