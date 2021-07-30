package com.github.alexsvdk.graphit.core

interface ChatUpdateReceiver<U: ChatUpdate, Bot> {
    fun receiveUpdate(chatUpdate: U, bot: Bot)
}