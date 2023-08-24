package com.yukihiro.droidkaigi2023.ui.error

import androidx.annotation.StringRes
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

interface ErrorHandler {
    fun handle(
        @StringRes matter: Int,
        exception: Throwable
    ): ErrorState
}