package com.tinyfight.emoji.kmp.di

import com.tinyfight.emoji.kmp.viewmodel.ShareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual inline fun <reified T : ShareViewModel> Module.shareViewModel(
    noinline definition: Definition<T>
): KoinDefinition<T> {
    return viewModel(null, definition)
}