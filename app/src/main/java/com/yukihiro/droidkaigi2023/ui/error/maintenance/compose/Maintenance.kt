package com.yukihiro.droidkaigi2023.ui.error.maintenance.compose

import androidx.compose.runtime.Composable
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.listener.MaintenanceNavigationListener
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.maintenance.MaintenanceDestination

@Composable
fun Maintenance(
    state: MaintenanceState,
    listener: MaintenanceNavigationListener
) {
    val dispatcher = LocalNavigationDispatcher.current
    dispatcher.navigate(MaintenanceDestination)
    listener.onNavigatedMaintenance(state)
}