package com.tinyfight.emoji.kmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class ShareViewModel actual constructor() : ViewModel() {
    actual val sharedViewModelScope: CoroutineScope = viewModelScope
}