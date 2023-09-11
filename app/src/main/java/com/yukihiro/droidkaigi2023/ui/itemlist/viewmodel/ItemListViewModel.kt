package com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.model.Account
import com.yukihiro.droidkaigi2023.domain.repository.AccountRepository
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.domain.usecase.itemlist.FetchItemListUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.itemlist.FetchItemListUseCaseResult
import com.yukihiro.droidkaigi2023.ui.common.navigation.Destination
import com.yukihiro.droidkaigi2023.ui.error.ErrorListener
import com.yukihiro.droidkaigi2023.ui.error.ErrorStateHelper
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import com.yukihiro.droidkaigi2023.ui.itemlist.compose.state.ItemListUiState
import com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel.error.ItemListErrorStates
import com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel.error.ItemListErrorHandler
import com.yukihiro.droidkaigi2023.ui.login.compose.LoginDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val itemRepository: ItemRepository,
    private val fetchItemListUseCase: FetchItemListUseCase
) : ViewModel(), ErrorListener {
    val errorStateHelper = ErrorStateHelper(ItemListErrorHandler, this)

    private val _itemListUiStateFlow = MutableStateFlow(ItemListUiState.default())
    val itemListUiStateFlow: StateFlow<ItemListUiState> = _itemListUiStateFlow.asStateFlow()

    private val _navigateFlow = MutableSharedFlow<Destination>()
    val navigateFlow: SharedFlow<Destination> = _navigateFlow

    var account: Account? = null

    init {
        viewModelScope.launch {
            runCatching {
                accountRepository.getCurrentAccount()
            }.fold(
                onSuccess = { account = it },
                onFailure = {
                    errorStateHelper.handleException(it)
                }
            )
        }

        viewModelScope.launch{
            itemRepository.itemList(account ?: return@launch)
                .catch {
                    errorStateHelper.handleException(it)
                }
                .collectLatest { itemList ->
                    _itemListUiStateFlow.update { it.copy(itemList = itemList) }
                }
        }

        viewModelScope.launch {
            when (val result = fetchItemListUseCase.invoke()) {
                is FetchItemListUseCaseResult.Success -> { }

                is FetchItemListUseCaseResult.Failure -> {
                    Log.e("Error", result.exception.toString())
                    errorStateHelper.handleException(result.exception)
                }
            }
        }
    }

    override fun onClickErrorDialogConfirm(state: ErrorDialogState) {
        if (state == ItemListErrorStates.ItemList.NotLogin) {
            viewModelScope.launch {
                _navigateFlow.emit(LoginDestination)
            }
        }
    }

    override fun onClickErrorDialogDismiss(state: ErrorDialogState) {

    }

    override fun onErrorSnackBarDismiss(state: ErrorSnackBarState) {

    }

    override fun onNavigatedMaintenance(state: MaintenanceState) {}
}