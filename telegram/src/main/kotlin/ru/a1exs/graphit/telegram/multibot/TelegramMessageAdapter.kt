package ru.a1exs.graphit.telegram.multibot

import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot
import ru.a1exs.graphit.multibot.MultiBotChatUpdate
import ru.a1exs.graphit.multibot.message.*
import ru.a1exs.graphit.telegram.maxSize

class TelegramMessageAdapter(
    val rawMessage: Message,
    sender: AbsSender,
    bot: TelegramBot,
) : MultiBotChatUpdate(rawMessage.chatId.toString(), extractMessageComponents(rawMessage, sender, bot)) {

    override val messageId: String
        get() = rawMessage.messageId.toString()

    override val from = TelegramUserInfoAdapter(rawMessage.from, sender, bot)

}

private fun extractMessageComponents(message: Message, sender: AbsSender, bot: TelegramBot) =
    mutableListOf<MessageComponent>().apply {
        if (message.hasText())
            add(TextMessageComponent(message.text))

        if (message.hasPhoto()) message.photo.maxSize()?.let {
            val url = sender.execute(GetFile(it.fileId)).getFileUrl(bot.botToken)
            val dataComponent = DataMessageComponent.fromUrl(url)
            add(ImageMessageComponent(dataComponent))
        }

        if (message.hasPoll())
            add(
                PollMessageComponent(
                    message.poll.options.map {
                        VoteData(
                            it.text,
                            it.voterCount,
                        )
                    },
                    message.poll.isClosed,
                    message.poll.isAnonymous,
                    message.poll.question,
                )
            )
    }