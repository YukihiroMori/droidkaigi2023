package com.yukihiro.droidkaigi2023.ui.login.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukihiro.droidkaigi2023.R
import com.yukihiro.droidkaigi2023.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.ui.common.navigation.Destination
import com.yukihiro.droidkaigi2023.ui.error.ErrorStateHelper
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
import com.yukihiro.droidkaigi2023.ui.error.maintenance.compose.state.MaintenanceState
import com.yukihiro.droidkaigi2023.ui.error.snackbar.state.ErrorSnackBarState
import com.yukihiro.droidkaigi2023.ui.maintenance.MaintenanceDestination
import com.yukihiro.droidkaigi2023.ui.login.compose.listener.LoginListener
import com.yukihiro.droidkaigi2023.ui.login.compose.state.LoginUiState
import com.yukihiro.droidkaigi2023.ui.login.viewmodel.error.LoginErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import okio.IOException
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LoginListener {
    val errorStateHelper = ErrorStateHelper(LoginErrorHandler,this)

    private val _loginUiStateFlow = MutableStateFlow(LoginUiState.default())
    val loginUiStateFlow: StateFlow<LoginUiState> = _loginUiStateFlow.asStateFlow()

    private val _navigateFlow = MutableSharedFlow<Destination>()
    val navigateFlow: SharedFlow<Destination> = _navigateFlow

    fun onResume() {
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            IOException()
        )
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            IOException()
        )
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            AccountException.WrongPassword
        )
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            IOException()
        )
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            AccountException.WrongPassword
        )
        errorStateHelper.handleError(
            R.string.error_dialog_matter_login,
            IllegalStateException()
        )
    }

    override fun onEditEmail(email: String) {
        _loginUiStateFlow.updateAndGet { it.copy(email = email, isNotFoundAccount = false) }
    }

    override fun onEditPassword(password: String) {
        _loginUiStateFlow.updateAndGet { it.copy(password = password, isWrongPassword = false) }
    }

    override fun onClickLogin() {
        login()
    }

    override fun onClickErrorDialogConfirm(state: ErrorDialogState) {
    }

    override fun onClickErrorDialogDismiss(state: ErrorDialogState) {
    }

    override fun onErrorSnackBarDismiss(state: ErrorSnackBarState) {
    }

    override fun onNavigatedMaintenance(state : MaintenanceState) {
    }

    private fun login() {
        viewModelScope.launch {
            val email = _loginUiStateFlow.value.email
            val password = _loginUiStateFlow.value.password

            val result = loginUseCase(
                email.toSecret(),
                password.toSecret()
            )
            when (result) {
                is LoginUseCaseResult.Success -> {
                    _navigateFlow.emit(MaintenanceDestination)
                }

                is LoginUseCaseResult.Failure -> {
                    when(result.exception){
                        is AccountException.NotRegistered ->
                            _loginUiStateFlow.updateAndGet { it.copy(isNotFoundAccount = true) }
                        is AccountException.WrongPassword ->
                            _loginUiStateFlow.updateAndGet { it.copy(isWrongPassword = true) }
                        else ->
                            errorStateHelper.handleError(
                                R.string.error_dialog_matter_login,
                                result.exception
                            )
                    }
                }
            }
        }
    }
}