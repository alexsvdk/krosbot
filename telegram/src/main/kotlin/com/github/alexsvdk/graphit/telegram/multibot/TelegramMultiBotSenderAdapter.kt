package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.core.message.*
import com.github.alexsvdk.graphit.core.message.DataMessageComponent.Type.*
import com.github.alexsvdk.graphit.core.message.KeyboardMessageComponent.Type.INLINE
import com.github.alexsvdk.graphit.core.message.KeyboardMessageComponent.Type.KEYBOARD
import com.github.alexsvdk.graphit.core.sender.MessageSender
import com.github.alexsvdk.graphit.core.sender.SenderCall
import com.github.alexsvdk.graphit.core.sender.SenderCall.Type.*
import com.github.alexsvdk.graphit.telegram.inputFile
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll
import org.telegram.telegrambots.meta.api.methods.send.*
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.bots.AbsSender

class TelegramMultiBotSenderAdapter(
    val sender: AbsSender,
) : MessageSender {

    protected fun sendMessage(senderCall: SenderCall) {

        val sentIds = mutableListOf<String>()

        val chatId = senderCall.chatId
        val data = senderCall.outgoingMessages!!

        var textToSend = (data.find { it is OutgoingText } as? OutgoingText)?.text

        var replyMarkupToSend = data.filterIsInstance<OutgoingKeyboard>().firstOrNull()?.let {
            when (it.type) {
                KEYBOARD -> ReplyKeyboardMarkup(
                    it.buttons.map { KeyboardRow(it.map { KeyboardButton(it.text) }) }
                )
                INLINE -> InlineKeyboardMarkup(
                    it.buttons.map {
                        it.map {
                            InlineKeyboardButton(
                                it.text,
                                it.url,
                                it.callbackData,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                        }.toList()
                    }.toList()
                )
            }
        }

        data.filterIsInstance<OutgoingData>().forEach {

            when (it.type) {
                IMAGE -> {
                    val send = SendPhoto.builder().apply {
                        chatId(chatId)
                        photo(it.inputFile)
                        if (textToSend != null) {
                            caption(textToSend)
                            textToSend = null
                        }
                        if (replyMarkupToSend != null) {
                            replyMarkup(replyMarkupToSend)
                            replyMarkupToSend = null
                        }
                    }.build()
                    sentIds.add(
                        sender.execute(send).messageId.toString()
                    )
                }
                VIDEO -> {
                    val send = SendVideo.builder().apply {
                        chatId(chatId)
                        video(it.inputFile)
                        if (textToSend != null) {
                            caption(textToSend)
                            textToSend = null
                        }
                        if (replyMarkupToSend != null) {
                            replyMarkup(replyMarkupToSend)
                            replyMarkupToSend = null
                        }
                    }.build()
                    sentIds.add(
                        sender.execute(send).messageId.toString()
                    )
                }
                AUDIO -> {
                    val send = SendAudio.builder().apply {
                        chatId(chatId)
                        audio(it.inputFile)
                        if (textToSend != null) {
                            caption(textToSend)
                            textToSend = null
                        }
                        if (replyMarkupToSend != null) {
                            replyMarkup(replyMarkupToSend)
                            replyMarkupToSend = null
                        }
                    }.build()
                    sentIds.add(
                        sender.execute(send).messageId.toString()
                    )
                }
                VOICE -> {
                    val send = SendVoice.builder().apply {
                        chatId(chatId)
                        voice(it.inputFile)
                        if (textToSend != null) {
                            caption(textToSend)
                            textToSend = null
                        }
                        if (replyMarkupToSend != null) {
                            replyMarkup(replyMarkupToSend)
                            replyMarkupToSend = null
                        }
                    }.build()
                    sentIds.add(
                        sender.execute(send).messageId.toString()
                    )
                }
                DOCUMENT -> {
                    val send = SendDocument.builder().apply {
                        chatId(chatId)
                        document(it.inputFile)
                        if (textToSend != null) {
                            caption(textToSend)
                            textToSend = null
                        }
                        if (replyMarkupToSend != null) {
                            replyMarkup(replyMarkupToSend)
                            replyMarkupToSend = null
                        }
                    }.build()
                    sentIds.add(
                        sender.execute(send).messageId.toString()
                    )
                }
            }

        }

        data.filterIsInstance<OutgoingPoll>().forEach {
            val send = SendPoll.builder().apply {
                chatId(chatId)
                question(it.question ?: "")
                isAnonymous(it.isAnonymous)
                options(it.voteData.map { it.optionName })
                if (replyMarkupToSend != null) {
                    replyMarkup(replyMarkupToSend)
                    replyMarkupToSend = null
                }
            }
                .build()
            sentIds.add(
                sender.execute(send).messageId.toString()
            )
        }

        data.filterIsInstance<LocationMessageComponent>().forEach {
            val send = SendLocation.builder().apply {
                chatId(chatId)
                latitude(it.latitude)
                longitude(it.longitude)
                if (replyMarkupToSend != null) {
                    replyMarkup(replyMarkupToSend)
                    replyMarkupToSend = null
                }
            }.build()
            sentIds.add(
                sender.execute(send).messageId.toString()
            )
        }

        if (textToSend != null) {
            val sendMessage = SendMessage.builder().apply {
                chatId(chatId)
                if (replyMarkupToSend != null) {
                    replyMarkup(replyMarkupToSend)
                    replyMarkupToSend = null
                }
            }.text(textToSend!!).build()
            sentIds.add(
                sender.execute(sendMessage).messageId.toString()
            )
            textToSend = null
        }
        senderCall.result = SenderCall.Result(sentIds, null)
    }

    protected fun updateMessage(senderCall: SenderCall) {
        TODO("Not implemented")
    }

    protected fun removeMessage(senderCall: SenderCall) {
        val deleteMessage = DeleteMessage(senderCall.chatId, senderCall.messageId!!.toInt())
        sender.execute(deleteMessage)
        senderCall.result = SenderCall.Result(listOf(senderCall.messageId!!), null)
    }

    override fun executeCall(senderCall: SenderCall) {
        try {
            when (senderCall.type) {
                SEND -> sendMessage(senderCall)
                UPDATE -> updateMessage(senderCall)
                DELETE -> removeMessage(senderCall)
            }
        } catch (e: Exception) {
            senderCall.result = SenderCall.Result(senderCall.result?.messageIds, e)
        }
    }

}