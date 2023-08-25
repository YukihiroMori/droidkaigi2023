package com.yukihiro.droidkaigi2023.ui.error.compose

import androidx.compose.runtime.Composable
import com.yukihiro.droidkaigi2023.ui.error.ErrorListener
import com.yukihiro.droidkaigi2023.ui.error.compose.listener.ErrorSectionListener
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorSectionState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.dialog.compose.ErrorDialog
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.Maintenance
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.compose.ErrorSnackBar
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState

@Composable
fun ErrorSection(
    state: ErrorSectionState,
    listener: ErrorSectionListener
) {
    state.errorSet.forEach {error ->
        when (error) {
            is ErrorDialogState -> ErrorDialog(
                state = error,
                listener = listener
            )

            is ErrorSnackBarState -> ErrorSnackBar(
                state = error,
                listener = listener
            )

            is MaintenanceState -> Maintenance(
                state = error,
                listener = listener
            )
        }
    }
}