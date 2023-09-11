package com.yukihiro.droidkaigi2023.ui.error.compose.state

data class ErrorSectionState(
    val errorSet: Set<ErrorState>
){
    companion object {
        fun default() = ErrorSectionState(errorSet = emptySet())
    }
}
