package com.github.alexsvdk.graphit.state

data class ChatState<T> (
    val chatId: String,
    val data: T
)