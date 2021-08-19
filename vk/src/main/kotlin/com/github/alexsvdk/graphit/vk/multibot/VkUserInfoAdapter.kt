package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotUserInfo
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Message

class VkUserInfoAdapter(
    val raw: Message,
    val vk: VkApiClient,
    val actor: GroupActor,
) : MultiBotUserInfo {

    val userInfo by lazy {
        vk.users().get(actor).userIds(raw.userId.toString()).execute().firstOrNull()
    }

    override val firstName: String?
        get() = userInfo?.firstName
    override val lastName: String?
        get() = userInfo?.lastName
    override val nickName: String
        get() = "id${raw.userId}"
    override val imageUrl: String?
        get() = userInfo?.photoMaxOrig

}