package com.yukihiro.droidkaigi2023.ui.error.dialog.state

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

    data class ErrorDialogState(
        @StringRes
        val matter: Int,
        @StringRes
        val reason: Int,
        @StringRes
        val confirmText: Int = R.string.error_dialog_ok,
        @StringRes
        val dismissText: Int = R.string.error_dialog_cancel,
        val isEnableDismiss: Boolean = false
    ): ErrorState
