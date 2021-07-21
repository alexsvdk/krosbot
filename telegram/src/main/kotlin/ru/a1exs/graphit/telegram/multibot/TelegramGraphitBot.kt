package ru.a1exs.graphit.telegram.multibot

import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import ru.a1exs.graphit.core.BasicUpdateCreator
import ru.a1exs.graphit.core.ChatUpdateCreator

abstract class TelegramGraphitBot(
    botOptions: DefaultBotOptions? = null,
    private val updateCreator: BasicUpdateCreator<TelegramMessageAdapter, TelegramMultiBotSenderAdapter> = BasicUpdateCreator()
) : TelegramLongPollingBot(botOptions ?: DefaultBotOptions()),
    ChatUpdateCreator<TelegramMessageAdapter, TelegramMultiBotSenderAdapter> by updateCreator {

    private val sender = TelegramMultiBotSenderAdapter(this)

    override fun onUpdateReceived(update: Update?) {
        update ?: return
        var res: TelegramMessageAdapter? = null

        if (update.hasMessage())
            res = TelegramMessageAdapter(update.message, this, this)

        if (res != null)
            updateCreator.publishChatUpdate(res, sender)
    }

}