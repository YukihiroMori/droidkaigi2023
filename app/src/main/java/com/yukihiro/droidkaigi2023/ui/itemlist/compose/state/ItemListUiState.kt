package com.yukihiro.droidkaigi2023.ui.itemlist.compose.state

import com.yukihiro.droidkaigi2023.domain.model.Item

data class ItemListUiState(
    val itemList: List<Item>
){
    companion object {
        fun default() = ItemListUiState(
            itemList = emptyList()
        )
    }
}
