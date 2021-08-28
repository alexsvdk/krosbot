package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.core.bot.ChatUpdate
import com.github.alexsvdk.graphit.core.message.DataMessageComponent
import com.github.alexsvdk.graphit.core.message.IncomingLocation
import com.github.alexsvdk.graphit.core.message.IncomingMessageComponent
import com.github.alexsvdk.graphit.core.message.IncomingText
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot

class TelegramMessageAdapter(
    val rawMessage: Message,
    private val sender: AbsSender,
    private val bot: TelegramBot,
) : ChatUpdate {

    override val messageId by lazy { rawMessage.messageId.toString() }

    override val chatInfo by lazy { TelegramChatInfoAdapter(rawMessage.chat) }

    override val from by lazy { TelegramUserInfoAdapter(rawMessage.from, sender, bot) }

    override val incomingComponents by lazy {
        mutableListOf<IncomingMessageComponent>().apply {
            if (rawMessage.hasText())
                add(IncomingText(rawMessage.text))

            if (rawMessage.hasPhoto())
                add(TelegramIncomingPhoto(rawMessage.photo, sender, bot))

            if (rawMessage.hasLocation())
                add(IncomingLocation(rawMessage.location.latitude, rawMessage.location.longitude))

            if (rawMessage.hasVideo())
                add(
                    TelegramDataAdapter(
                        rawMessage.video.fileId,
                        sender,
                        bot,
                        DataMessageComponent.Type.VIDEO
                    )
                )

            if (rawMessage.hasAudio())
                add(
                    TelegramDataAdapter(
                        rawMessage.audio.fileId,
                        sender,
                        bot,
                        DataMessageComponent.Type.AUDIO
                    )
                )

            if (rawMessage.hasVoice())
                add(
                    TelegramDataAdapter(
                        rawMessage.voice.fileId,
                        sender,
                        bot,
                        DataMessageComponent.Type.VOICE
                    )
                )

            if (rawMessage.hasVoice())
                add(
                    TelegramDataAdapter(
                        rawMessage.document.fileId,
                        sender,
                        bot,
                        DataMessageComponent.Type.DOCUMENT
                    )
                )
        }
    }
}