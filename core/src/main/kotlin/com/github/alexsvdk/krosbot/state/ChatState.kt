package com.github.alexsvdk.krosbot.state

data class ChatState<T> (
    val chatId: String,
    val data: T
)