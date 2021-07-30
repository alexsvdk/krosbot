package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.core.ChatUpdate
import com.github.alexsvdk.graphit.multibot.message.ImageMessageComponent
import com.github.alexsvdk.graphit.multibot.message.MessageComponent
import com.github.alexsvdk.graphit.multibot.message.TextMessageComponent

abstract class MultiBotChatUpdate(chatId: String, messageComponents: List<MessageComponent>) : ChatUpdate(chatId),
    List<MessageComponent> by messageComponents {

    abstract val messageId: String?
    abstract val from: MultiBotUserInfo

    open val text: String?
        get() = filterIsInstance<TextMessageComponent>().firstOrNull()?.text

    open val images: List<ImageMessageComponent>
        get() = filterIsInstance<ImageMessageComponent>()

}