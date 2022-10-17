package com.github.alexsvdk.krosbot.vk.multibot

import com.github.alexsvdk.krosbot.core.UserInfo
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.objects.messages.Message

class VkUserInfoAdapter(
    val raw: Message,
    val vk: VkApiClient,
    val actor: GroupActor,
) : UserInfo {

    val userInfo by lazy {
        vk.users().get(actor).userIds(raw.fromId.toString()).execute().firstOrNull()
    }
    override val userId: String get() = raw.fromId.toString()
    override val isBot: Boolean = false
    override val firstName: String? get() = userInfo?.firstName
    override val lastName: String? get() = userInfo?.lastName
    override val nickName: String get() = "id${raw.fromId}"
    override val imageUrl: String? get() = userInfo?.photoMaxOrig.toString()

}