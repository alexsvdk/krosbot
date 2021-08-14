package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.multibot.message.MessageComponent

interface MultiBotSender {

    fun sendMessage(chatId: String, data: List<MessageComponent>)

    fun updateMessage(chatId: String, messageId: String, data: List<MessageComponent>)

    fun removeMessage(chatId: String, messageId: String)

}