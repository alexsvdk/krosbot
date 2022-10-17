package com.github.alexsvdk.krosbot.telegram.multibot

import com.github.alexsvdk.krosbot.core.ChatInfo
import org.telegram.telegrambots.meta.api.objects.Chat

class TelegramChatInfoAdapter(
    val rawChat: Chat
) : ChatInfo {

    override val id: String get() = rawChat.id.toString()

    override val isGroup get() = rawChat.isGroupChat || rawChat.isSuperGroupChat

    override val name: String = rawChat.firstName + if (rawChat.lastName != null) " ${rawChat.lastName}" else ""

    override val members get() = null

}