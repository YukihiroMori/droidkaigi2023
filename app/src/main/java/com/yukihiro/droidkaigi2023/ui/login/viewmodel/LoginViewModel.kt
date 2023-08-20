package com.yukihiro.droidkaigi2023.ui.login.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yukihiro.droidkaigi2023.architecture.toSecret
import com.yukihiro.droidkaigi2023.domain.repository.account.AccountRepository
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCase
import com.yukihiro.droidkaigi2023.domain.usecase.login.LoginUseCaseResult
import com.yukihiro.droidkaigi2023.ui.common.navigation.Destination
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState
import com.yukihiro.droidkaigi2023.ui.error.compose.state.NoError
import com.yukihiro.droidkaigi2023.ui.error.dialog.state.ErrorDialogState
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LoginListener {
    private val errorHandler = LoginErrorHandler

    private val _loginUiStateFlow = MutableStateFlow(LoginUiState.default())
    val loginUiStateFlow: StateFlow<LoginUiState> = _loginUiStateFlow.asStateFlow()

    private val _errorStateFlow = MutableStateFlow<ErrorState>(NoError)
    val errorStateFlow: StateFlow<ErrorState> = _errorStateFlow.asStateFlow()

    private val _navigateFlow= MutableSharedFlow<Destination>()
    val navigateFlow: SharedFlow<Destination> = _navigateFlow

    private fun handleError(exception: Throwable) {
        _errorStateFlow.update { errorHandler.handle(exception) }
    }

    fun onResume(){
        //_errorStateFlow.update { MaintenanceState }
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
        _errorStateFlow.update { NoError }
    }

    override fun onClickErrorDialogDismiss(state: ErrorDialogState) {
        _errorStateFlow.update { NoError }
    }

    override fun onErrorSnackBarDismiss() {
        _errorStateFlow.update { NoError }
    }

    override fun onNavigatedMaintenance() {
        _errorStateFlow.update { NoError }
    }

    private fun login() {
        viewModelScope.launch {
            val email = _loginUiStateFlow.value.email
            val password = _loginUiStateFlow.value.password

            val result = loginUseCase(
                email.toSecret(),
                password.toSecret()
            )
            when(result){
                is LoginUseCaseResult.Success -> {
                    _navigateFlow.emit(MaintenanceDestination)
                }
                is LoginUseCaseResult.Failure.NotRegistered -> {
                    _loginUiStateFlow.updateAndGet { it.copy(isNotFoundAccount = true) }
                }
                is LoginUseCaseResult.Failure.WrongPassword -> {
                    _loginUiStateFlow.updateAndGet { it.copy(isWrongPassword = true) }
                }
                is LoginUseCaseResult.Failure.ServerError -> {

                }
                is LoginUseCaseResult.Failure.Unexpected -> {
                    handleError(result.exception)
                }
            }
        }
    }
}