package com.github.alexsvdk.krosbot.core.message

open class OutgoingKeyboard(
    override val buttons: List<List<KeyboardMessageComponent.Button>>,
    override val type: KeyboardMessageComponent.Type = KeyboardMessageComponent.Type.KEYBOARD
) : KeyboardMessageComponent, OutgoingMessageComponent {

    constructor(
        vararg buttons: List<KeyboardMessageComponent.Button>,
        type: KeyboardMessageComponent.Type = KeyboardMessageComponent.Type.KEYBOARD
    ) : this(buttons.toList(), type)

    constructor(
        vararg buttons: KeyboardMessageComponent.Button,
        type: KeyboardMessageComponent.Type = KeyboardMessageComponent.Type.KEYBOARD
    ) : this(buttons.toList(), type = type)

    constructor(
        vararg buttons: String,
        type: KeyboardMessageComponent.Type = KeyboardMessageComponent.Type.KEYBOARD
    ) : this(buttons.map { KeyboardMessageComponent.Button(it) }.toList(), type = type)

}