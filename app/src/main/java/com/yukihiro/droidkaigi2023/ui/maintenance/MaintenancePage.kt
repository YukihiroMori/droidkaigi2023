package com.yukihiro.droidkaigi2023.ui.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yukihiro.droidkaigi2023.ui.common.navigation.LocalNavigationDispatcher
import com.yukihiro.droidkaigi2023.ui.login.compose.LoginDestination

@Composable
fun MaintenancePage() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val dispatcher = LocalNavigationDispatcher.current
        Text(
            "ただいまメンテナンス中です。",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "ご利用の皆様にはご迷惑をおかけし、申し訳ございません。",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            "メンテナンス終了までしばらくお待ちください。",
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                dispatcher.popBackStack(LoginDestination)
            }
        ) {
            Text(
                "戻る",
                fontWeight = FontWeight.Bold
            )
        }
    }
}