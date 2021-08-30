package com.github.alexsvdk.graphit.core.message.utils

import com.github.alexsvdk.graphit.core.message.*

val Iterable<MessageComponent>.textComponents get() = filterIsInstance<TextMessageComponent>()
val Iterable<MessageComponent>.dataComponents get() = filterIsInstance<DataMessageComponent>()
val Iterable<MessageComponent>.keyboardComponents get() = filterIsInstance<KeyboardMessageComponent>()
val Iterable<MessageComponent>.locationComponents get() = filterIsInstance<LocationMessageComponent>()
val Iterable<MessageComponent>.multisizeMediaComponents get() = filterIsInstance<MultiSizeMediaMessageComponent>()
val Iterable<MessageComponent>.pollComponents get() = filterIsInstance<PollMessageComponent>()
val Iterable<MessageComponent>.customComponents get() = filterIsInstance<CustomMessageComponent>()

val Iterable<MessageComponent>.textComponent
    get() = find { it is TextMessageComponent } as TextMessageComponent?
val Iterable<MessageComponent>.dataComponent
    get() = find { it is DataMessageComponent } as DataMessageComponent?
val Iterable<MessageComponent>.keyboardComponent
    get() = find { it is KeyboardMessageComponent } as KeyboardMessageComponent?
val Iterable<MessageComponent>.locationComponent
    get() = find { it is LocationMessageComponent } as LocationMessageComponent?
val Iterable<MessageComponent>.multisizeMediaComponent
    get() = find { it is MultiSizeMediaMessageComponent } as MultiSizeMediaMessageComponent?
val Iterable<MessageComponent>.pollComponent
    get() = find { it is PollMessageComponent } as PollMessageComponent?
val Iterable<MessageComponent>.customComponent
    get() = find { it is CustomMessageComponent } as CustomMessageComponent?