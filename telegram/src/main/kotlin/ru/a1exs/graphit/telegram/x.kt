package ru.a1exs.graphit.telegram

import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import ru.a1exs.graphit.multibot.message.DataMessageComponent

fun Iterable<PhotoSize>.maxSize(): PhotoSize? = maxByOrNull { it.width }

val DataMessageComponent.inputFile
    get() = InputFile(inputStream, name)