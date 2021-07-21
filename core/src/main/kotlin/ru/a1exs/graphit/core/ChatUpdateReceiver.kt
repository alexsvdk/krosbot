package ru.a1exs.graphit.core

interface ChatUpdateReceiver<U: ChatUpdate, Bot> {
    fun receiveUpdate(chatUpdate: U, bot: Bot)
}