package com.yukihiro.droidkaigi2023.ui.error.maintenance.listener

import com.yukihiro.droidkaigi2023.ui.error.maintenance.state.MaintenanceState

interface MaintenanceNavigationListener {
    fun onNavigatedMaintenance(state: MaintenanceState)
}