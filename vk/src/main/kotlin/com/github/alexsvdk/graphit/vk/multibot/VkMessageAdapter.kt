package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotChatUpdate
import com.github.alexsvdk.graphit.multibot.MultiBotUserInfo
import com.github.alexsvdk.graphit.multibot.message.*
import com.vk.api.sdk.objects.messages.Message

class VkMessageAdapter(val raw: Message) : MultiBotChatUpdate(
    raw.chatId.toString(),
    extractComponents(raw)
) {

    override val messageId: String
        get() = raw.randomId.toString()
    override val from: MultiBotUserInfo = VkUserInfoAdapter(raw)
}


private fun extractComponents(msg: Message): List<MessageComponent> {
    val res = mutableListOf<MessageComponent>()

    msg.body?.let {
        res.add(TextMessageComponent(it))
    }

    msg.attachments?.forEach { attachment ->
        attachment?.photo?.let {
            val url = it.sizes?.maxByOrNull { it.width }?.src ?: return@let
            val data = DataMessageComponent.fromUrl(url)
            res.add(ImageMessageComponent(data))
        }
        attachment?.doc?.let {
            val data = DataMessageComponent.fromUrl(it.url)
            res.add(ImageMessageComponent(data))
        }
    }

    msg.geo?.let {
        res.add(LocationMessageComponent(it.place.latitude.toDouble(), it.place.longitude.toDouble()))
    }


    return res;
}