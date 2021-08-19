package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotSender
import com.github.alexsvdk.graphit.multibot.message.*
import com.github.alexsvdk.graphit.multibot.textComponent
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor

class VkMultiBotSenderAdapter(
    private val vk: VkApiClient,
    private val actor: GroupActor
) : MultiBotSender {

    override fun sendMessage(chatId: String, data: List<MessageComponent>) {
        var sendQuery = vk.messages().send(actor).peerId(chatId.toInt())

        data.filterIsInstance<LocationMessageComponent>().forEach {
            sendQuery.lat(it.latitude.toFloat())
            sendQuery.lng(it.longitude.toFloat())
        }

        data.filterIsInstance<KeyboardMessageComponent>().forEach {
            val keyboard = mapOf(
                "one_time" to false,
                "inline" to false,
                "buttons" to it.buttons.map {it.map {
                    mapOf(
                        "action" to mapOf(
                            "type" to "text",
                            "label" to it,
                        ),
                        "color" to "primary",
                    )
                }}
            )
            sendQuery.unsafeParam("keyboard", keyboard)
        }

        data.textComponent?.let {
            sendQuery.message(it.text)
        }

        sendQuery.execute()
    }

    override fun updateMessage(chatId: String, messageId: String, data: List<MessageComponent>) {
        TODO("Not yet implemented")
    }

    override fun removeMessage(chatId: String, messageId: String) {
        TODO("Not yet implemented")
    }

}