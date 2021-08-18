package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotUserInfo
import com.vk.api.sdk.objects.messages.Message

class VkUserInfoAdapter(
    val raw: Message
) : MultiBotUserInfo {

    override val firstName: String?
        get() = TODO("Not yet implemented")
    override val lastName: String?
        get() = TODO("Not yet implemented")
    override val nickName: String?
        get() = TODO("Not yet implemented")
    override val imageUrl: String?
        get() = TODO("Not yet implemented")

}