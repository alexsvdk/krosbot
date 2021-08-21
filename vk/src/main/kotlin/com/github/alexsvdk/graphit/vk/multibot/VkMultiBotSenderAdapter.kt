package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotSender
import com.github.alexsvdk.graphit.multibot.message.KeyboardMessageComponent
import com.github.alexsvdk.graphit.multibot.message.LocationMessageComponent
import com.github.alexsvdk.graphit.multibot.message.MessageComponent
import com.github.alexsvdk.graphit.multibot.textComponent
import com.google.gson.Gson
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor

class VkMultiBotSenderAdapter(
    private val vk: VkApiClient,
    private val actor: GroupActor
) : MultiBotSender {

    private val gson = Gson()

    override fun sendMessage(chatId: String, data: List<MessageComponent>) {
        var sendQuery = vk.messages().send(actor).peerId(chatId.toInt())

        data.filterIsInstance<LocationMessageComponent>().forEach {
            sendQuery.lat(it.latitude.toFloat())
            sendQuery.lng(it.longitude.toFloat())
        }

        data.filterIsInstance<KeyboardMessageComponent>().forEach {
            val keyboard = gson.toJson(
                mapOf(
                    "one_time" to false,
                    "inline" to false,
                    "buttons" to it.buttons.map {
                        it.map {
                            mapOf(
                                "action" to mapOf(
                                    "type" to "text",
                                    "label" to it,
                                ),
                                "color" to "primary",
                            )
                        }.toList()
                    }.toList()
                )
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