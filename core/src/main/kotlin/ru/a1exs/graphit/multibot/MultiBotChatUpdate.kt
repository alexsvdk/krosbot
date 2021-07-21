package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.core.ChatUpdate
import ru.a1exs.graphit.multibot.message.ImageMessageComponent
import ru.a1exs.graphit.multibot.message.MessageComponent
import ru.a1exs.graphit.multibot.message.TextMessageComponent

abstract class MultiBotChatUpdate(chatId: String, messageComponents: List<MessageComponent>) : ChatUpdate(chatId),
    List<MessageComponent> by messageComponents {

    abstract val messageId: String?
    abstract val from: MultiBotUserInfo

    open val text: String?
        get() = filterIsInstance<TextMessageComponent>().firstOrNull()?.text

    open val images: List<ImageMessageComponent>
        get() = filterIsInstance<ImageMessageComponent>()

}