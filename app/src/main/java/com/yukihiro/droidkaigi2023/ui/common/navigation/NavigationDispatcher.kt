package com.yukihiro.droidkaigi2023.ui.common.navigation

interface NavigationDispatcher {
    fun navigate(destination: Destination)

    fun popBackStack(destination: Destination? = null)
}