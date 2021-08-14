package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.multibot.MultiBotSender
import com.github.alexsvdk.graphit.multibot.message.*
import com.github.alexsvdk.graphit.multibot.textComponent
import com.github.alexsvdk.graphit.telegram.inputFile
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll
import org.telegram.telegrambots.meta.api.methods.send.*
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.bots.AbsSender

class TelegramMultiBotSenderAdapter(
    val sender: AbsSender,
) : MultiBotSender {

    override fun sendMessage(chatId: String, data: List<MessageComponent>) {

        var textSent = false

        var replyMarkup = data.filterIsInstance<KeyboardMessageComponent>().firstOrNull()?.let {
            ReplyKeyboardMarkup(
                it.buttons.map { KeyboardRow(it.map { KeyboardButton(it) }) }
            )
        }

        data.filterIsInstance<ImageMessageComponent>().forEach {
            val send = SendPhoto.builder().apply {
                chatId(chatId)
                photo(it.inputFile)
                if (!textSent && data.textComponent != null) {
                    caption(data.textComponent!!.text)
                    textSent = true
                }
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }.build()
            sender.execute(send)

        }

        data.filterIsInstance<VideoMessageComponent>().forEach {
            val send = SendVideo.builder().apply {
                chatId(chatId)
                video(it.inputFile)
                if (!textSent && data.textComponent != null) {
                    caption(data.textComponent!!.text)
                    textSent = true
                }
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }.build()
            sender.execute(send)
        }

        data.filterIsInstance<DocumentMessageComponent>().forEach {
            val send = SendDocument.builder().apply {
                chatId(chatId)
                document(it.inputFile)
                if (!textSent && data.textComponent != null) {
                    caption(data.textComponent!!.text)
                    textSent = true
                }
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }.build()
            sender.execute(send)
        }

        data.filterIsInstance<AudioMessageComponent>().forEach {
            val send = SendAudio.builder().apply {
                chatId(chatId)
                audio(it.inputFile)
                if (!textSent && data.textComponent != null) {
                    caption(data.textComponent!!.text)
                    textSent = true
                }
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }.build()
            sender.execute(send)
        }

        data.filterIsInstance<PollMessageComponent>().forEach {
            val send = SendPoll.builder().apply {
                chatId(chatId)
                question(it.question ?: "")
                isClosed(it.isClosed)
                isAnonymous(it.isAnonymous)
                options(it.voteData.map { it.optionName })
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }
                .build()
            sender.execute(send)
        }

        data.filterIsInstance<LocationMessageComponent>().forEach {
            val send = SendLocation.builder().apply {
                latitude(it.latitude)
                longitude(it.longitude)
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }
                .build()
            sender.execute(send)
        }

        if (!textSent) data.textComponent?.let {
            val sendMessage = SendMessage.builder().apply {
                chatId(chatId)
                text(it.text)
                if (replyMarkup != null) {
                    replyMarkup(replyMarkup)
                    replyMarkup = null
                }
            }.build()
            sender.execute(sendMessage)
            textSent = true
        }

    }

    override fun updateMessage(chatId: String, messageId: String, data: List<MessageComponent>) {
        TODO("Not implemented")
    }

    override fun removeMessage(chatId: String, messageId: String) {
        val deleteMessage = DeleteMessage(chatId,messageId.toInt())
        sender.execute(deleteMessage)
    }

}