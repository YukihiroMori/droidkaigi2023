package com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel.error

import android.database.sqlite.SQLiteException
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yukihiro.droidkaigi2023.domain.exception.AccountException
import com.yukihiro.droidkaigi2023.domain.exception.ItemException
import com.yukihiro.droidkaigi2023.ui.error.ErrorHandler
import com.yukihiro.droidkaigi2023.ui.error.compose.state.ErrorState

object ItemListErrorHandler : ErrorHandler {
    override fun handle(exception: Throwable): ErrorState {
        Firebase.crashlytics.recordException(exception)

        return when (exception) {
            is AccountException.NotLoggedIn -> ItemListErrorStates.ItemList.NotLogin
            is ItemException.DatabaseAccessException -> ItemListErrorStates.SnackBar.LoadFail
            else -> ItemListErrorStates.SnackBar.Unexpected
        }
    }
}