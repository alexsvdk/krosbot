package com.github.alexsvdk.graphit.multibot.message

data class TextMessageComponent(
    val text: String,
    val type: Type = Type.PLAIN,
) : MessageComponent {
    enum class Type {
        /// Plain text
        PLAIN,

        /// Markdown text
        MD,

        /// HTML text
        HTML
    }
}