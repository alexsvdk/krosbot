package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.core.UserInfo
import com.github.alexsvdk.graphit.core.bot.ChatUpdate
import com.github.alexsvdk.graphit.core.message.*
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Message
import javax.xml.crypto.Data

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

    msg.body?.let {
        res.add(IncomingText(it))
    }

    msg.attachments?.forEach { attachment ->
        attachment?.photo?.let {
            res.add(VkIncomingPhoto(it))
        }
        attachment?.doc?.let {
            val data = IncomingData.fromUrl(it.url, DataMessageComponent.Type.DOCUMENT)
            res.add(data)
        }
        attachment?.audio?.let {
            val data = IncomingData.fromUrl(it.url, DataMessageComponent.Type.AUDIO)
            res.add(data)
        }
    }
    msg.geo?.let {
        res.add(IncomingLocation(it.place.latitude.toDouble(), it.place.longitude.toDouble()))
    }

    return res;
}