package com.yukihiro.droidkaigi2023.ui.common.navigation

import androidx.compose.runtime.compositionLocalOf

val LocalNavigationDispatcher = compositionLocalOf<NavigationDispatcher> { error("Not Provide NavigationDispatcher") }