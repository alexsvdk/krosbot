package com.github.alexsvdk.graphit.vk.multibot

import com.github.alexsvdk.graphit.core.message.KeyboardMessageComponent
import com.github.alexsvdk.graphit.core.message.OutgoingKeyboard
import com.github.alexsvdk.graphit.core.message.OutgoingLocation
import com.github.alexsvdk.graphit.core.message.OutgoingText
import com.github.alexsvdk.graphit.core.sender.MessageSender
import com.github.alexsvdk.graphit.core.sender.SenderCall
import com.google.gson.Gson
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor

class VkMultiBotSenderAdapter(
    private val vk: VkApiClient,
    private val actor: GroupActor
) : MessageSender {

    private val gson = Gson()

    protected fun sendMessage(senderCall: SenderCall) {

        val data = senderCall.outgoingMessages!!
        val chatId = senderCall.chatId

        var sendQuery = vk.messages().send(actor).peerId(chatId.toInt())

        data.forEach {
            when (it) {
                is OutgoingText -> {
                    sendQuery.message(it.text)
                }
                is OutgoingKeyboard -> {
                    val keyboard = gson.toJson(
                        mapOf(
                            "one_time" to false,
                            "inline" to (it.type == KeyboardMessageComponent.Type.INLINE),
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
                is OutgoingLocation -> {
                    sendQuery.lat(it.latitude.toFloat())
                    sendQuery.lng(it.longitude.toFloat())
                }
            }
        }

        val res = sendQuery.execute()
        senderCall.result = SenderCall.Result(listOf(res.toString()), null)

    }

    protected fun updateMessage(senderCall: SenderCall) {
        TODO("Not yet implemented")
    }

    protected fun removeMessage(senderCall: SenderCall) {
        val delete = vk.messages().delete(actor).messageIds(senderCall.messageId!!.toInt())
        delete.execute()
        senderCall.result = SenderCall.Result(listOf(senderCall.messageId!!), null)
    }

    override fun executeCall(senderCall: SenderCall) {
        try {
            when (senderCall.type) {
                SenderCall.Type.SEND -> sendMessage(senderCall)
                SenderCall.Type.UPDATE -> updateMessage(senderCall)
                SenderCall.Type.DELETE -> removeMessage(senderCall)
            }
        } catch (e: Exception) {
            senderCall.result = SenderCall.Result(senderCall.result?.messageIds, e)
        }
    }

}