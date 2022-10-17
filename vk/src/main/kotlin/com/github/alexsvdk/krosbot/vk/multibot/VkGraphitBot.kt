package com.github.alexsvdk.krosbot.vk.multibot

import com.github.alexsvdk.krosbot.core.bot.BasicChatUpdateCreator
import com.github.alexsvdk.krosbot.core.bot.ChatUpdateCreator
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.objects.messages.Message


open class VkGraphitBot(
    private val vk: VkApiClient,
    groupId: Int,
    accessToken: String,
    private val pollingDelayMs: Long = 1000,
    private val updateCreator: BasicChatUpdateCreator = BasicChatUpdateCreator(),
) : ChatUpdateCreator by updateCreator {
    private val actor = GroupActor(groupId, accessToken)
    private val sender = VkMultiBotSenderAdapter(vk, actor)

    private var ts = vk.messages().getLongPollServer(actor).execute().getTs()
    private var maxMsgId = 0

    fun start() = Thread {
        while (true) {
            try {
                val msg = getMessage()
                if (msg == null) {
                    if (pollingDelayMs > 0) Thread.sleep(pollingDelayMs)
                    continue
                }
                updateCreator.emitUpdate(VkMessageAdapter(msg, vk, actor), sender)
            } catch (e: Exception) {
                e.printStackTrace()
                if (pollingDelayMs > 0) Thread.sleep(pollingDelayMs)
            }
        }
    }.start()


    private fun getMessage(): Message? {
        val eventsQuery = vk.messages()
            .getLongPollHistory(actor)
            .ts(ts)
        if (maxMsgId > 0) {
            eventsQuery.maxMsgId(maxMsgId)
        }
        val messages: List<Message> = eventsQuery.execute().messages.items

        if (!messages.isEmpty()) {
            try {
                ts = vk.messages()
                    .getLongPollServer(actor)
                    .execute()
                    .ts
            } catch (e: ClientException) {
                e.printStackTrace()
            }
        }
        if (!messages.isEmpty() && !messages[0].isOut()) {
            val messageId: Int = messages[0].getId()
            if (messageId > maxMsgId) {
                maxMsgId = messageId
            }
            return messages[0]
        }
        return null
    }

}