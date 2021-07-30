package com.github.alexsvdk.graphit.telegram

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import com.github.alexsvdk.graphit.multibot.message.DataMessageComponent

fun Iterable<PhotoSize>.maxSize(): PhotoSize? = maxByOrNull { it.width }

val DataMessageComponent.inputFile
    get() = InputFile(inputStream, name)