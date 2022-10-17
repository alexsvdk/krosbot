package com.github.alexsvdk.krosbot.core.message

typealias OutgoingText = BidirectionalText
typealias IncomingText = BidirectionalText

open class BidirectionalText(
    override val text: String,
    override val type: TextMessageComponent.Type = TextMessageComponent.Type.PLAIN,
) : TextMessageComponent, BidirectionalMessageComponent