package com.yukihiro.droidkaigi2023.ui.login.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.error.compose.ErrorSection
import com.yukihiro.droidkaigi2023.ui.login.viewmodel.LoginViewModel


@Composable
fun LoginPage(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginUiState = viewModel.loginUiStateFlow.collectAsState()
    val errorState = viewModel.errorStateHelper.errorStateFlow.collectAsState()

    val dispatcher = LocalNavigationDispatcher.current
    val destination = viewModel.navigateFlow.collectAsState(initial = null)
    destination.value?.let { dispatcher.navigate(it) }

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
            viewModel.onResume()
        }
    }

    LoginTemplate(
        state = loginUiState.value,
        listener = viewModel
    )

    ErrorSection(
        state = errorState.value,
        listener = viewModel,
    )
}