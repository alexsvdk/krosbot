package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.core.ChatUpdate
import ru.a1exs.graphit.multibot.message.MessageComponent

abstract class MultiBotChatUpdate(chatId: String) : ChatUpdate(chatId) {

    abstract val userInfo: MultiBotUserInfo

    abstract val updateComponents: List<MessageComponent>
}