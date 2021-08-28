package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.core.ChatInfo
import com.github.alexsvdk.graphit.core.UserInfo
import com.vk.api.sdk.objects.messages.Message

class VkChatInfoAdapter(
    val rawMsg: Message
) : ChatInfo {
    override val id by lazy { rawMsg.chatId.toString() }
    override val isGroup = false
    override val name: String? = rawMsg.title
    override val members: List<UserInfo>? = null
}