package com.yukihiro.droidkaigi2023.ui.error.snackbar.listener

import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState

interface ErrorSnackBarListener {
    fun onErrorSnackBarDismiss(state: ErrorSnackBarState)
}

