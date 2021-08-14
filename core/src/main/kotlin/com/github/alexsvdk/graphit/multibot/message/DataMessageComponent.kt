package com.github.alexsvdk.graphit.multibot.message

import java.io.File
import java.io.InputStream
import java.net.URL

open class DataMessageComponent(
    val inputStream: InputStream,
    val name: String? = null,
) : MessageComponent {
    companion object {
        fun fromFile(file: File): DataMessageComponent = DataMessageComponent(file.inputStream())
        fun fromUrl(url: URL): DataMessageComponent = DataMessageComponent(url.openStream())
        fun fromUrl(url: String): DataMessageComponent = fromUrl(URL(url))
    }
}