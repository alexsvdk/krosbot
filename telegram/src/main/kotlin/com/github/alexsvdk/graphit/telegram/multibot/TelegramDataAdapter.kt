package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.core.message.DataMessageComponent
import com.github.alexsvdk.graphit.core.message.IncomingData
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot
import java.net.URL

class TelegramDataAdapter(
    fileId: String,
    sender: AbsSender,
    bot: TelegramBot,
    type: DataMessageComponent.Type,
    fileName: String? = null,
) : IncomingData(type, fileName, {
    val url = URL(sender.execute(GetFile(fileId)).getFileUrl(bot.botToken))
    url.openStream()
})