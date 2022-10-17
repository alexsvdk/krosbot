package com.github.alexsvdk.krosbot.core.message.utils

import com.github.alexsvdk.krosbot.core.bot.ChatUpdate
import com.github.alexsvdk.krosbot.core.message.*

//multiple components my message
val Iterable<MessageComponent>.textComponents get() = filterIsInstance<TextMessageComponent>()
val Iterable<MessageComponent>.dataComponents get() = filterIsInstance<DataMessageComponent>()
val Iterable<MessageComponent>.keyboardComponents get() = filterIsInstance<KeyboardMessageComponent>()
val Iterable<MessageComponent>.locationComponents get() = filterIsInstance<LocationMessageComponent>()
val Iterable<MessageComponent>.multisizeMediaComponents get() = filterIsInstance<MultiSizeMediaMessageComponent>()
val Iterable<MessageComponent>.pollComponents get() = filterIsInstance<PollMessageComponent>()
val Iterable<MessageComponent>.customComponents get() = filterIsInstance<CustomMessageComponent>()

//component by message
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


//multiple components my message
val ChatUpdate.textComponents get() = incomingComponents.textComponents
val ChatUpdate.dataComponents get() = incomingComponents.dataComponents
val ChatUpdate.keyboardComponents get() = incomingComponents.keyboardComponents
val ChatUpdate.locationComponents get() = incomingComponents.locationComponents
val ChatUpdate.multisizeMediaComponents get() = incomingComponents.multisizeMediaComponents
val ChatUpdate.pollComponents get() = incomingComponents.pollComponents
val ChatUpdate.customComponents get() = incomingComponents.customComponents

//component by message
val ChatUpdate.textComponent
    get() = incomingComponents.textComponent
val ChatUpdate.dataComponent
    get() = incomingComponents.dataComponent
val ChatUpdate.keyboardComponent
    get() = incomingComponents.keyboardComponent
val ChatUpdate.locationComponent
    get() = incomingComponents.locationComponent
val ChatUpdate.multisizeMediaComponent
    get() = incomingComponents.multisizeMediaComponent
val ChatUpdate.pollComponent
    get() = incomingComponents.pollComponent
val ChatUpdate.customComponent
    get() = incomingComponents.customComponent