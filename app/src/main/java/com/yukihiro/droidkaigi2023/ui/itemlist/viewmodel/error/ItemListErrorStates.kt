package com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel.error

import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.ui.error.dialog.CommonErrorDialogStates
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.CommonErrorSnackBarStates
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState

object ItemListErrorStates {
    object SnackBar {
        val LoadFail = ErrorSnackBarState(
            message = R.string.error_snack_bar_message_load_fail
        )
        val Unexpected = ErrorSnackBarState(
            message = R.string.error_snack_bar_message_unexpected
        )
    }

    object ItemList {
        val NotLogin = ErrorDialogState(
            matter = R.string.error_dialog_matter_load_item,
            reason = R.string.error_dialog_reason_not_login,
            confirmText = R.string.error_dialog_login,
            isEnableDismiss = true
        )
    }
}