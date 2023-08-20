package com.yukihiro.droidkaigi2023.ui.error.snackbar.compose

import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.yukihiro.droidkaigi2023.ui.common.LocalSnackBarHostState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.listener.ErrorSnackBarListener
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import kotlinx.coroutines.launch

@Composable
fun ErrorSnackBar(
    state: ErrorSnackBarState,
    listener: ErrorSnackBarListener
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val hostState = LocalSnackBarHostState.current

    LaunchedEffect(hostState, state) {
        scope.launch {
            val result = hostState.showSnackbar(
                message = context.getString(state.message),
                duration = state.duration
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {}
                SnackbarResult.Dismissed -> {
                    listener.onErrorSnackBarDismiss()
                }
            }
        }
    }
}