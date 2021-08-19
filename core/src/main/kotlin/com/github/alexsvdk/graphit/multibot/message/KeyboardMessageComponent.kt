package com.github.alexsvdk.graphit.multibot.message

class KeyboardMessageComponent(
    val buttons: List<List<String>>,
) : MessageComponent{
    constructor(vararg buttons: List<String>): this(buttons.toList())
    constructor(vararg buttons: String): this(buttons.toList())
}