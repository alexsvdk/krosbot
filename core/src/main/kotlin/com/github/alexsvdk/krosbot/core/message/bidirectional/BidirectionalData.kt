package com.github.alexsvdk.krosbot.core.message

import java.io.InputStream
import java.net.URL
import java.net.URLConnection

typealias OutgoingData = BidirectionalData
typealias IncomingData = BidirectionalData

open class BidirectionalData(
    override val type: DataMessageComponent.Type,
    override val fileName: String? = null,
    inputStreamLazy: () -> InputStream,
) : DataMessageComponent, BidirectionalMessageComponent {

    override val inputStream by lazy(inputStreamLazy)

    companion object {

        fun fromUrlConnection(
            connection: URLConnection,
            type: DataMessageComponent.Type,
            fileName: String? = null,
        ): BidirectionalData = BidirectionalData(type, fileName) { connection.inputStream }

        fun fromUrl(
            url: URL,
            type: DataMessageComponent.Type,
            fileName: String? = null,
        ): BidirectionalData = BidirectionalData(type, fileName) { url.openStream() }

        fun fromUrl(
            url: String,
            type: DataMessageComponent.Type,
            fileName: String? = null,
        ): BidirectionalData = fromUrl(URL(url), type, fileName)

    }

}