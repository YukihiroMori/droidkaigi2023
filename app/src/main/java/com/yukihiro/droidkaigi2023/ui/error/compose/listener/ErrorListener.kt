package com.yukihiro.droidkaigi2023.ui.error.compose.listener

import com.yukihiro.droidkaigi2023.ui.error.dialog.listener.ErrorDialogListener
import com.yukihiro.droidkaigi2023.ui.error.maintenance.listener.MaintenanceNavigationListener
import com.yukihiro.droidkaigi2023.ui.error.snackbar.listener.ErrorSnackBarListener

interface ErrorListener : ErrorDialogListener, ErrorSnackBarListener, MaintenanceNavigationListener