package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.multibot.message.MessageComponent

interface MultiBotSender {

    fun sendMessage(chatId: String, data: List<MessageComponent>)

    fun updateMessage(messageId: String, data: List<MessageComponent>)

    fun removeMessage(messageId: String)

}