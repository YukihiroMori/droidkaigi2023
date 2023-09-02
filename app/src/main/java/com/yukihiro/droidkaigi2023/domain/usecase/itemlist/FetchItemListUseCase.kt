package com.yukihiro.droidkaigi2023.domain.usecase.itemlist

interface FetchItemListUseCase {
    suspend operator fun invoke(): FetchItemListUseCaseResult
}