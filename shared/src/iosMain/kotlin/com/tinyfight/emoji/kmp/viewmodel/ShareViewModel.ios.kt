package com.tinyfight.emoji.kmp.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual open class ShareViewModel actual constructor() {
    actual val sharedViewModelScope: CoroutineScope = MainScope()
}