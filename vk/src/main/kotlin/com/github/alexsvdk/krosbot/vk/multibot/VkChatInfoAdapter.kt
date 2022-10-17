package com.github.alexsvdk.krosbot.vk.multibot

import com.github.alexsvdk.krosbot.core.ChatInfo
import com.github.alexsvdk.krosbot.core.UserInfo
import com.vk.api.sdk.objects.messages.Message

class VkChatInfoAdapter(
    val rawMsg: Message
) : ChatInfo {
    override val id by lazy { rawMsg.fromId.toString() }
    override val isGroup = false
    override val name: String? = null
    override val members: List<UserInfo>? = null
}