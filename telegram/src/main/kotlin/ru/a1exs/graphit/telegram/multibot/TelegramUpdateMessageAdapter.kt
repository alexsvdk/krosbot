package ru.a1exs.graphit.telegram.multibot

import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot
import ru.a1exs.graphit.multibot.MultiBotChatUpdate
import ru.a1exs.graphit.multibot.message.DataMessageComponent
import ru.a1exs.graphit.multibot.message.ImageMessageComponent
import ru.a1exs.graphit.multibot.message.MessageComponent
import ru.a1exs.graphit.multibot.message.TextMessageComponent
import ru.a1exs.graphit.telegram.maxSize

class TelegramUpdateMessageAdapter(
    val message: Message,
    private val sender: AbsSender,
    private val bot: TelegramBot,
) : MultiBotChatUpdate(message.chatId.toString()) {

    override val from = TelegramUserInfoAdapter(message.from, sender, bot)
    override val messageComponents = mutableListOf<MessageComponent>().apply {

        if (message.hasText())
            add(TextMessageComponent(message.text))

        if (message.hasPhoto()) message.photo.maxSize()?.let {
            val url = sender.execute(GetFile(it.fileId)).getFileUrl(bot.botToken)
            val dataComponent = DataMessageComponent.fromUrl(url)
            add(ImageMessageComponent(dataComponent))
        }

    }

}