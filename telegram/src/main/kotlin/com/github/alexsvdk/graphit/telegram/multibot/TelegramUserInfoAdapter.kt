package com.github.alexsvdk.graphit.telegram.multibot

import com.github.alexsvdk.graphit.core.UserInfo
import com.github.alexsvdk.graphit.telegram.maxSize
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot

class TelegramUserInfoAdapter(
    val rawUser: User,
    private val sender: AbsSender,
    private val bot: TelegramBot,
) : UserInfo {

    override val userId: String get() = rawUser.id.toString()

    override val isBot: Boolean get() = rawUser.isBot

    override val firstName get() = rawUser.firstName

    override val lastName get() = rawUser.lastName

    override val nickName get() = rawUser.userName

    override val imageUrl: String? by lazy {
        val photo = sender.execute(GetUserProfilePhotos(rawUser.id, 0, 1)).photos.firstOrNull() ?: return@lazy null
        return@lazy sender.execute(GetFile(photo.maxSize()?.fileId ?: return@lazy null)).getFileUrl(bot.botToken)
    }

}