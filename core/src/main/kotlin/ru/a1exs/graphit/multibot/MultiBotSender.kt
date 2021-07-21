package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.multibot.message.MessageComponent

interface MultiBotSender {

    fun sendMessage(chatId: String, data: List<MessageComponent>)

    fun updateMessage(messageId: String, data: List<MessageComponent>)

    fun removeMessage(messageId: String)

}