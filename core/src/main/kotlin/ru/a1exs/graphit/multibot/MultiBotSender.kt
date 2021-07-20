package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.multibot.message.ImageMessageComponent
import ru.a1exs.graphit.multibot.message.MessageComponent
import ru.a1exs.graphit.multibot.message.TextMessageComponent

class MultiBotComponentsValidationError(override val message: String?) : Error()

interface MultiBotSender {

    fun sendMessage(chatId: String, data: List<MessageComponent>)

    fun validateMessageComponents(data: List<MessageComponent>) {
        if (data.filterIsInstance<TextMessageComponent>().size > 1)
            throw MultiBotComponentsValidationError("Ony one TextMessageComponent could be passed")
    }

}

val List<MessageComponent>.textComponent: TextMessageComponent?
    get() = filterIsInstance<TextMessageComponent>().firstOrNull()

val List<MessageComponent>.imageComponent: ImageMessageComponent?
    get() = filterIsInstance<ImageMessageComponent>().firstOrNull()