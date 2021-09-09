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
import com.vk.api.sdk.objects.messages.Keyboard
import com.vk.api.sdk.objects.messages.KeyboardButton
import com.vk.api.sdk.objects.messages.KeyboardButtonAction
import com.vk.api.sdk.objects.messages.KeyboardButtonColor

open class VkMultiBotSenderAdapter(
    private val vk: VkApiClient,
    private val actor: GroupActor
) : MessageSender {

    private val gson = Gson()

    protected fun sendMessage(senderCall: SenderCall) {

        val data = senderCall.outgoingMessages!!
        val chatId = senderCall.chatId

        val sendQuery = vk.messages()
            .send(actor)
            .peerId(chatId.toInt())
            .randomId((Math.random() * Int.MAX_VALUE).toInt())

        data.forEach {
            when (it) {
                is OutgoingText -> {
                    sendQuery.message(it.text)
                }
                is OutgoingKeyboard -> {
                    val keyboard = Keyboard().apply {
                        inline = it.type == KeyboardMessageComponent.Type.INLINE
                        buttons = it.buttons.map {
                            it.map {
                                KeyboardButton().apply {
                                    color = KeyboardButtonColor.DEFAULT
                                    action = KeyboardButtonAction().apply {
                                        label = it.text
                                        payload = it.callbackData
                                        link = it.url
                                    }
                                }
                            }.toList()
                        }.toList()
                    }
                    sendQuery.keyboard(keyboard)
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