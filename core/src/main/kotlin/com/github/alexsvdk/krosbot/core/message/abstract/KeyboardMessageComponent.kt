package com.github.alexsvdk.krosbot.core.message

interface KeyboardMessageComponent : MessageComponent {

    enum class Type {
        KEYBOARD, INLINE
    }

    open class Button(
        val text: String,
        val url: String? = null,
        val callbackData: String? = null,
    )

    val buttons: List<List<Button>>
    val type: Type

}