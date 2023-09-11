package com.yukihiro.droidkaigi2023.ui.itemlist.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yukihiro.droidkaigi2023.domain.model.Item
import com.yukihiro.droidkaigi2023.ui.itemlist.compose.state.ItemListUiState

@Composable
fun ItemListTemplate(state: ItemListUiState) {
    ItemGrid(state = state)
}

@Composable
fun ItemGrid(state: ItemListUiState) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(state.itemList) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        LoadingImage(
            image = item.image,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun LoadingImage(image: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val isLoading = remember { mutableStateOf(true) }
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            onLoading = { isLoading.value = true },
            onSuccess = { isLoading.value = false },
            onError = { isLoading.value = false }
        )
        if (isLoading.value) {
            CircularProgressIndicator()
        }
    }
}