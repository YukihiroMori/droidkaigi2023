package com.yukihiro.droidkaigi2023.ui.error.snackbar.state

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

    data class ErrorSnackBarState(
        @StringRes
        val message: Int,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ) : ErrorState

