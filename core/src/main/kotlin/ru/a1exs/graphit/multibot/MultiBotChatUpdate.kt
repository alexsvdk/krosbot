package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.core.ChatUpdate
import ru.a1exs.graphit.multibot.message.ImageMessageComponent
import ru.a1exs.graphit.multibot.message.MessageComponent
import ru.a1exs.graphit.multibot.message.TextMessageComponent

abstract class MultiBotChatUpdate(chatId: String) : ChatUpdate(chatId) {

    abstract val from: MultiBotUserInfo
    abstract val messageComponents: List<MessageComponent>
    val isNotEmpty get() = messageComponents.isNotEmpty()

    open val text: String?
        get() = messageComponents.filterIsInstance<TextMessageComponent>().firstOrNull()?.text

    open val images: List<ImageMessageComponent>
        get() = messageComponents.filterIsInstance<ImageMessageComponent>()
}