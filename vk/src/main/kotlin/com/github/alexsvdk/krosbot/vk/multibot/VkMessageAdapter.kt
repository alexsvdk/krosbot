package com.github.alexsvdk.krosbot.vk.multibot

import com.github.alexsvdk.krosbot.core.UserInfo
import com.github.alexsvdk.krosbot.core.bot.ChatUpdate
import com.github.alexsvdk.krosbot.core.message.*
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Message

class VkMessageAdapter(
    val raw: Message,
    vk: VkApiClient,
    actor: GroupActor,
) : ChatUpdate {
    override val messageId: String
        get() = raw.randomId.toString()
    override val incomingComponents by lazy { extractComponents(raw) }

    override val chatInfo by lazy { VkChatInfoAdapter(raw) }
    override val from: UserInfo = VkUserInfoAdapter(raw, vk, actor)
}


private fun extractComponents(msg: Message): List<IncomingMessageComponent> {
    val res = mutableListOf<IncomingMessageComponent>()

    msg.text?.let {
        res.add(IncomingText(it))
    }

    msg.attachments?.forEach { attachment ->
        attachment?.photo?.let {
            res.add(VkIncomingPhoto(it))
        }
        attachment?.doc?.let {
            val data = IncomingData.fromUrl(it.url.toURL(), DataMessageComponent.Type.DOCUMENT)
            res.add(data)
        }
        attachment?.audio?.let {
            val data = IncomingData.fromUrl(it.url.toURL(), DataMessageComponent.Type.AUDIO)
            res.add(data)
        }
    }
    msg.geo?.let {
        res.add(IncomingLocation(it.place.latitude.toDouble(), it.place.longitude.toDouble()))
    }

    return res;
}