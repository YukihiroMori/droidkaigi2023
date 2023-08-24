package com.yukihiro.droidkaigi2023.ui.error

import com.yukihiro.droidkaigi2023.ui.error.dialog.listener.ErrorDialogListener
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.listener.MaintenanceNavigationListener
import com.yukihiro.droidkaigi2023.ui.error.snackbar.listener.ErrorSnackBarListener

interface ErrorListener : ErrorDialogListener, ErrorSnackBarListener, MaintenanceNavigationListener