package com.yukihiro.droidkaigi2023.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yukihiro.droidkaigi2023.ui.common.LocalSnackBarHostState
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.maintenance.MaintenanceDestination
import com.yukihiro.droidkaigi2023.ui.maintenance.MaintenancePage
import com.yukihiro.droidkaigi2023.ui.itemlist.compose.ItemListPage
import com.yukihiro.droidkaigi2023.ui.itemlist.compose.ItemListDestination
import com.yukihiro.droidkaigi2023.ui.theme.Droidkaigi2023Theme
import com.yukihiro.droidkaigi2023.ui.login.compose.LoginDestination
import com.yukihiro.droidkaigi2023.ui.login.compose.LoginPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Droidkaigi2023Theme {
        val navController = rememberNavController()
        CompositionLocalProvider(
            LocalNavigationDispatcher provides MainNavigationDispatcher(navController)
        ) {
            NavHost(
                navController = navController,
                startDestination = LoginDestination.route,
            ) {
                composable(route = LoginDestination.route) {
                    LoginPage()
                }

                composable(route = ItemListDestination.route) {
                    ItemListPage()
                }

                composable(route = MaintenanceDestination.route) {
                    MaintenancePage()
                }
            }
        }
    }
}