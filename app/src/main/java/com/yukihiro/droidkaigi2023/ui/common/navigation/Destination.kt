package com.yukihiro.droidkaigi2023.ui.common.navigation

annotation class NavigateRoute

interface Destination {
    @NavigateRoute
    val route: String
}