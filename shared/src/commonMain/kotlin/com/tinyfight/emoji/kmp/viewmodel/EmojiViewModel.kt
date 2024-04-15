package com.tinyfight.emoji.kmp.viewmodel

import com.tinyfight.emoji.kmp.data.EmojiItem
import com.tinyfight.emoji.kmp.data.EmojiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmojiViewModel(private val emojiRepository: EmojiRepository) : ShareViewModel() {
    private val _items = MutableStateFlow<List<EmojiItem>>(listOf())

    val items = _items.asStateFlow()

     init {
         sharedViewModelScope.launch {
             _items.update {
                 emojiRepository.getEmojis()
             }
         }
     }
}