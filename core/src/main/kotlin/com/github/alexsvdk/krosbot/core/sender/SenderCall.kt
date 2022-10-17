package com.github.alexsvdk.krosbot.core.sender

import com.github.alexsvdk.krosbot.core.message.OutgoingMessageComponent

open class SenderCall(
    val chatId: String,
    val type: Type,
    val outgoingMessages: List<OutgoingMessageComponent>? = null,
    messageId: String? = null,
) {

    enum class Type {
        SEND, UPDATE, DELETE
    }

    data class Result(
        val messageIds: List<String>?,
        val exception: Exception?
    ) {
        val messageSent get() = !messageIds.isNullOrEmpty()
        val hasException get() = exception != null

        val firstMessageId get() = messageIds?.firstOrNull()
    }

    var result: Result? = null
        set(value) {
            assert(field == null) { "Result can be only set once" }
            field = value
            if (messageId == null && value?.messageSent == true)
                messageId = value.firstMessageId
        }

    var messageId: String?
        private set

    init {
        this.messageId = messageId
        when (type) {
            Type.SEND -> {
                assert(messageId == null)
                assert(outgoingMessages != null && outgoingMessages.isNotEmpty())
            }
            Type.UPDATE -> {
                assert(messageId != null)
                assert(outgoingMessages != null && outgoingMessages.isNotEmpty())
            }
            Type.DELETE -> {
                assert(messageId != null)
            }
        }
    }

}