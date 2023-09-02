package com.yukihiro.droidkaigi2023.ui.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarHostState = compositionLocalOf<SnackbarHostState> { error("Not Provide SnackBarHostState") }

