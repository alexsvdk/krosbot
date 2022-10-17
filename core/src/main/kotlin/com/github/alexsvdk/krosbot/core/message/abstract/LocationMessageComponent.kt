package com.github.alexsvdk.krosbot.core.message


interface LocationMessageComponent : MessageComponent {
    val latitude: Double
    val longitude: Double
}