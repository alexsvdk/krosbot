package com.github.alexsvdk.graphit.core.sender

import com.github.alexsvdk.graphit.core.message.OutgoingMessageComponent

interface MessageSender {

    fun sendMessage(
        chatId: String,
        message: List<OutgoingMessageComponent>,
    ): SenderCall = SenderCall(chatId, SenderCall.Type.SEND, message).apply {
        executeCall(this)
    }

    fun updateMessage(
        chatId: String,
        messageId: String,
        message: List<OutgoingMessageComponent>,
    ): SenderCall = SenderCall(chatId, SenderCall.Type.UPDATE, message, chatId).apply {
        executeCall(this)
    }

    fun removeMessage(
        chatId: String,
        messageId: String
    ): SenderCall = SenderCall(chatId, SenderCall.Type.DELETE, messageId = messageId).apply {
        executeCall(this)
    }

    fun executeCall(senderCall: SenderCall)

}