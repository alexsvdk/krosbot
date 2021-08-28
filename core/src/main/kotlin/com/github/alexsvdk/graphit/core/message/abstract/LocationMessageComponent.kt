package com.github.alexsvdk.graphit.core.message


interface LocationMessageComponent : MessageComponent {
    val latitude: Double
    val longitude: Double
}