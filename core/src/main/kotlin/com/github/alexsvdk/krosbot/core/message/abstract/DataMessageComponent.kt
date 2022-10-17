package com.github.alexsvdk.krosbot.core.message

import java.io.InputStream


interface DataMessageComponent : MessageComponent {

    enum class Type {
        IMAGE, VIDEO, AUDIO, VOICE, DOCUMENT,
    }

    val inputStream: InputStream
    val type: Type
    val fileName: String?

    /*
companion object {
fun fromFile(file: File, type: Type): DataMessageComponent =
    DataMessageComponent(lazy { file.inputStream() }, type)

fun fromUrl(url: URL, type: Type): DataMessageComponent =
    DataMessageComponent(lazy { url.openStream() }, type)

fun fromUrl(url: String, type: Type): DataMessageComponent =
    fromUrl(URL(url), type)
}

     */
}