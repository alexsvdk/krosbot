package com.github.alexsvdk.krosbot.telegram.multibot

import com.github.alexsvdk.krosbot.core.message.DataMessageComponent
import com.github.alexsvdk.krosbot.core.message.IncomingMultiSizeMedia
import com.github.alexsvdk.krosbot.core.message.MultiSizeMediaMessageComponent
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot

class TelegramIncomingPhoto(
    val photo: MutableList<PhotoSize>,
    val sender: AbsSender,
    val bot: TelegramBot,
) : IncomingMultiSizeMedia {

    class TelegramPhotoSize(val rawSize: PhotoSize) :
        MultiSizeMediaMessageComponent.Size(rawSize.width, rawSize.height)

    override val sizes by lazy { photo.map { TelegramPhotoSize(it) } }

    override fun getData(size: MultiSizeMediaMessageComponent.Size): DataMessageComponent {
        assert(size is TelegramPhotoSize){"Telegram bot can process only TelegramPhotoSize"}
        return TelegramDataAdapter(
            (size as TelegramPhotoSize).rawSize.fileId,
            sender,
            bot,
            DataMessageComponent.Type.IMAGE,
            size.rawSize.fileId
        )
    }

}
