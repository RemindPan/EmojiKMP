package com.tinyfight.emoji.kmp.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class ShareViewModel() {
    val sharedViewModelScope: CoroutineScope
}