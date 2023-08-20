package com.yukihiro.droidkaigi2023.ui.error.compose.state

data class ErrorSectionState(
    val errorList: List<ErrorState>
){
    companion object {
        fun default() = ErrorSectionState(errorList = emptyList())
    }
}
