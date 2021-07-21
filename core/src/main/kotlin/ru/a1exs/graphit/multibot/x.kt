package ru.a1exs.graphit.multibot

import ru.a1exs.graphit.multibot.message.ImageMessageComponent
import ru.a1exs.graphit.multibot.message.MessageComponent
import ru.a1exs.graphit.multibot.message.PollMessageComponent
import ru.a1exs.graphit.multibot.message.TextMessageComponent

val List<MessageComponent>.textComponent: TextMessageComponent?
    get() = filterIsInstance<TextMessageComponent>().firstOrNull()

val List<MessageComponent>.imageComponent: ImageMessageComponent?
    get() = filterIsInstance<ImageMessageComponent>().firstOrNull()

val List<MessageComponent>.pollComponent: PollMessageComponent?
    get() = filterIsInstance<PollMessageComponent>().firstOrNull()