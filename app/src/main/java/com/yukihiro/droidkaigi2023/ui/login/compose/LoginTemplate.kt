package com.yukihiro.droidkaigi2023.ui.login.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.yukihiro.droidkaigi2023.ui.login.compose.listener.LoginListener
import com.yukihiro.droidkaigi2023.ui.login.compose.state.LoginUiState

@Composable
fun LoginTemplate(state: LoginUiState, listener: LoginListener) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title()
        Spacer(modifier = Modifier.height(96.dp))
        EmailInput(state = state, listener = listener)
        PasswordInput(state = state, listener = listener)
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(state = state, listener = listener)
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        text = "DroidKaigi2023",
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier,
    )
    Text(
        text = "error-handling-sample",
        fontFamily = FontFamily.SansSerif,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(state: LoginUiState, listener: LoginListener) {
    OutlinedTextField(
        value = state.email,
        onValueChange = { listener.onEditEmail(it) },
        label = {
            Text("email")
        },
        supportingText = {
            if (state.isNotFoundAccount) Text(text = "アカウントが登録さていません")
            else Text(text = "")
        },
        isError = state.isNotFoundAccount,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(state: LoginUiState, listener: LoginListener) {
    OutlinedTextField(
        value = state.password,
        onValueChange = { listener.onEditPassword(it) },
        label = {
            Text("password")
        },
        supportingText = {
            if (state.isWrongPassword) Text(text = "パスワードが間違っています")
            else Text(text = "")
        },
        isError = state.isWrongPassword,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
    )
}

@Composable
fun LoginButton(state: LoginUiState, listener: LoginListener) {
    Button(
        enabled = state.isEnableLogin,
        onClick = { listener.onClickLogin() }
    ) {
        Text(text = "ログイン", fontWeight = FontWeight.Bold)
    }
}