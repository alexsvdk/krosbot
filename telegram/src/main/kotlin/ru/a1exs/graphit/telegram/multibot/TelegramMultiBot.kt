package ru.a1exs.graphit.telegram.multibot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import ru.a1exs.graphit.multibot.ChatUpdateCreator
import ru.a1exs.graphit.multibot.MultiBotChatUpdate
import ru.a1exs.graphit.multibot.UpdateListener

abstract class TelegramMultiBot : TelegramLongPollingBot(), ChatUpdateCreator {

    override var updateListener: UpdateListener? = null

    override fun onUpdateReceived(update: Update?) {
        update ?: return
        var multiBotChatUpdate: MultiBotChatUpdate? = null

        if (update.hasMessage())
            multiBotChatUpdate = TelegramUpdateMessageAdapter(update.message, this, this)

        if (multiBotChatUpdate != null)
            updateListener?.invoke(multiBotChatUpdate)
    }

}