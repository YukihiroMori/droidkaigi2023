package com.yukihiro.droidkaigi2023.ui.login.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.yukihiro.droidkaigi2023.ui.common.LocalSnackBarHostState
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.error.compose.ErrorSection
import com.yukihiro.droidkaigi2023.ui.login.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiStateFlow.collectAsState()
    val errorSectionState = viewModel.errorStateHelper.errorSectionStateFlow.collectAsState()

    val dispatcher = LocalNavigationDispatcher.current
    val destination = viewModel.navigateFlow.collectAsState(initial = null)
    destination.value?.let { dispatcher.navigate(it) }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
            viewModel.onResume()
        }
    }

    val hostState = remember { SnackbarHostState() }
    CompositionLocalProvider(
        LocalSnackBarHostState provides hostState
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState) },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    LoginTemplate(
                        state = loginUiState.value,
                        listener = viewModel
                    )

                    ErrorSection(
                        state = errorSectionState.value,
                        listener = viewModel.errorStateHelper
                    )
                }
            }
        )
    }
}