package com.github.alexsvdk.graphit.multibot

import com.github.alexsvdk.graphit.multibot.message.ImageMessageComponent
import com.github.alexsvdk.graphit.multibot.message.MessageComponent
import com.github.alexsvdk.graphit.multibot.message.PollMessageComponent
import com.github.alexsvdk.graphit.multibot.message.TextMessageComponent

val List<MessageComponent>.textComponent: TextMessageComponent?
    get() = filterIsInstance<TextMessageComponent>().firstOrNull()

val List<MessageComponent>.imageComponent: ImageMessageComponent?
    get() = filterIsInstance<ImageMessageComponent>().firstOrNull()

val List<MessageComponent>.pollComponent: PollMessageComponent?
    get() = filterIsInstance<PollMessageComponent>().firstOrNull()