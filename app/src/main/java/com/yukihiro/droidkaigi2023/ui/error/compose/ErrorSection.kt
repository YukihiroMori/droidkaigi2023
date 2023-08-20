package com.yukihiro.droidkaigi2023.ui.error.compose

import androidx.compose.runtime.Composable
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.error.compose.listener.ErrorListener
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorSectionState
import com.yukihiro.droidkaigi2023.ui.error.dialog.compose.ErrorDialog
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.compose.ErrorSnackBar
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import com.yukihiro.droidkaigi2023.ui.maintenance.MaintenanceDestination

@Composable
fun ErrorSection(
    state: ErrorSectionState,
    listener: ErrorListener,
) {
    state.errorList.forEach {error ->
        when (error) {
            is ErrorDialogState -> ErrorDialog(
                state = error,
                listener = listener
            )

            is ErrorSnackBarState -> ErrorSnackBar(
                state = error,
                listener = listener
            )

            is MaintenanceState -> {
                val dispatcher = LocalNavigationDispatcher.current
                dispatcher.navigate(MaintenanceDestination)
                listener.onNavigatedMaintenance(error)
            }
        }
    }
}