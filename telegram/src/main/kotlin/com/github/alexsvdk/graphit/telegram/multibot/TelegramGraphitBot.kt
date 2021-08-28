package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.core.bot.BasicChatUpdateCreator
import com.github.alexsvdk.graphit.core.bot.ChatUpdateCreator
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

abstract class TelegramGraphitBot(
    botOptions: DefaultBotOptions? = null,
    private val updateCreator: BasicChatUpdateCreator = BasicChatUpdateCreator()
) : TelegramLongPollingBot(botOptions ?: DefaultBotOptions()), ChatUpdateCreator by updateCreator {

    private val sender = TelegramMultiBotSenderAdapter(this)

    override fun onUpdateReceived(update: Update?) {
        update ?: return
        var res: TelegramMessageAdapter? = null

        if (update.hasMessage())
            res = TelegramMessageAdapter(update.message, this, this)

        if (res != null)
            updateCreator.emitUpdate(res, sender)
    }

}