package com.yukihiro.droidkaigi2023.ui.error.dialog.listener

import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState

interface ErrorDialogListener {
    fun onClickErrorDialogConfirm(state: ErrorDialogState)
    fun onClickErrorDialogDismiss(state: ErrorDialogState)
}