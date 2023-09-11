package com.yukihiro.droidkaigi2023.ui.error.dialog.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.dialog.listener.ErrorDialogListener
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.theme.Typography

@Composable
fun ErrorDialog(state: ErrorDialogState, listener: ErrorDialogListener) {
    AlertDialog(
        title = {
            Text(
                text = stringResource(id = state.matter),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = stringResource(id = state.reason),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        onDismissRequest = {
            if(!state.isEnableDismiss) return@AlertDialog
            listener.onClickErrorDialogDismiss(state)
        },
        confirmButton = {
            TextButton(
                onClick = { listener.onClickErrorDialogConfirm(state) }
            ) {
                Text(
                    text = stringResource(state.confirmText),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            if(!state.isEnableDismiss) return@AlertDialog
            TextButton(
                onClick = { listener.onClickErrorDialogDismiss(state) }
            ) {
                Text(
                    text = stringResource(state.dismissText),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
    )
}