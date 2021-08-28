package com.github.alexsvdk.graphit.core.message

interface TextMessageComponent : MessageComponent {

    val text: String
    val type: Type

    enum class Type {
        /// Plain text
        PLAIN,

        /// Markdown text
        MD,

        /// HTML text
        HTML
    }

}