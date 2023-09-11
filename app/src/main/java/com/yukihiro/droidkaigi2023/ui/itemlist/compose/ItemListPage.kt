package com.yukihiro.droidkaigi2023.ui.itemlist.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yukihiro.droidkaigi2023.ui.common.LocalSnackBarHostState
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.error.compose.ErrorSection
import com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel.ItemListViewModel
import com.yukihiro.droidkaigi2023.ui.login.compose.LoginTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListPage(
    viewModel: ItemListViewModel = hiltViewModel()
) {
    val itemListState = viewModel.itemListUiStateFlow.collectAsState()
    val errorSectionState = viewModel.errorStateHelper.errorSectionStateFlow.collectAsState()

    val dispatcher = LocalNavigationDispatcher.current
    val destination = viewModel.navigateFlow.collectAsState(initial = null)
    destination.value?.let { dispatcher.navigate(it) }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val hostState = remember { SnackbarHostState() }
    CompositionLocalProvider(
        LocalSnackBarHostState provides hostState
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState) },
            topBar = {
                TopAppBar(
                    title = { Text(text = "ItemList") },
                    navigationIcon = {
                        IconButton(onClick = {
                            dispatcher.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    ItemListTemplate(
                        state = itemListState.value
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