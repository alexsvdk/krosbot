package com.github.alexsvdk.graphit.telegram

import com.github.alexsvdk.graphit.core.message.DataMessageComponent
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot

fun Iterable<PhotoSize>.maxSize(): PhotoSize? = maxByOrNull { it.width }

fun PhotoSize.getFile(sender: AbsSender) = sender.execute(GetFile(fileId))

val DataMessageComponent.inputFile get() = InputFile(inputStream, fileName)

fun getTgFileUrl(
    fileId: String,
    sender: AbsSender,
    bot: TelegramBot
) = sender.execute(GetFile(fileId)).getFileUrl(bot.botToken)