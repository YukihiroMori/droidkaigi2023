package com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.listener

import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState

interface MaintenanceNavigationListener {
    fun onNavigatedMaintenance(state: MaintenanceState)
}