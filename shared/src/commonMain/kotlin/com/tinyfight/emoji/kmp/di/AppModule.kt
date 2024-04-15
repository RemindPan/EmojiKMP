package com.tinyfight.emoji.kmp.di

import com.tinyfight.emoji.kmp.data.EmojiRepository
import com.tinyfight.emoji.kmp.data.httpClient
import com.tinyfight.emoji.kmp.viewmodel.EmojiViewModel
import com.tinyfight.emoji.kmp.viewmodel.ShareViewModel
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.module

expect inline fun <reified T : ShareViewModel> Module.shareViewModel(
    noinline definition: Definition<T>
): KoinDefinition<T>

val appModule = module {
    single {
        httpClient {
            expectSuccess = true
            defaultRequest {
                url("https://emojihub.yurace.pro") // Base URL for all requests
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single {
        EmojiRepository(get())
    }

    shareViewModel {
        EmojiViewModel(get())
    }
}