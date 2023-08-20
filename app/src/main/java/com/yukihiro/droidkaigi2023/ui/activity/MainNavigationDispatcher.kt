package com.yukihiro.droidkaigi2023.ui.activity

import androidx.navigation.NavController
import com.yukihiro.droidkaigi2023.ui.common.navigation.Destination
import com.yukihiro.droidkaigi2023.ui.common.navigation.NavigationDispatcher

class MainNavigationDispatcher(private val navController: NavController): NavigationDispatcher {
    override fun navigate(destination: Destination) {
        navController.navigate(destination.route)
    }
}