package com.yukihiro.droidkaigi2023.ui.error

import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

interface ErrorHandler {
    fun handle(exception: Throwable): ErrorState
}

