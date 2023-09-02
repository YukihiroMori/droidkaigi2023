package com.yukihiro.droidkaigi2023.domain.usecase.itemlist

sealed class FetchItemListUseCaseResult {
    object Success : FetchItemListUseCaseResult()

    class Failure(val exception: Throwable) : FetchItemListUseCaseResult()
}