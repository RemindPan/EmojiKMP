package com.tinyfight.emoji.kmp.android

import android.text.Html
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.tinyfight.emoji.kmp.data.EmojiItem
import com.tinyfight.emoji.kmp.viewmodel.EmojiViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiDisplayScreen() {

    val emojiHubViewModel: EmojiViewModel = koinViewModel()
    val items by emojiHubViewModel.items.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "EmojiDisplayPage")
        })
    }, content = { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items.size) { index ->
                Item(items[index])
            }
        }
    })
}

@Composable
private fun Item(emojiItem: EmojiItem) {
    Column(Modifier.padding(end = 16.dp, start = 16.dp, top = 10.dp)) {
        Text(text = "Name: ${emojiItem.name}")
        Text(text = "Group: ${emojiItem.group}")
        Text(text = "Category: ${emojiItem.category}")
        Text(
            text = Html.fromHtml(emojiItem.htmlCode[0], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }
}