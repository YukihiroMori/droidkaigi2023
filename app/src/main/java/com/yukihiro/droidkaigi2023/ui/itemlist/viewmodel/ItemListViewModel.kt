package com.yukihiro.droidkaigi2023.ui.itemlist.viewmodel

import androidx.lifecycle.ViewModel
import com.yukihiro.droidkaigi2023.domain.repository.ItemRepository
import com.yukihiro.droidkaigi2023.domain.usecase.itemlist.FetchItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val fetchItemListUseCase: FetchItemListUseCase
) : ViewModel(){

}