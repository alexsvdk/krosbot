package com.github.alexsvdk.graphit.telegram.multibot

import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot
import com.github.alexsvdk.graphit.multibot.MultiBotUserInfo
import com.github.alexsvdk.graphit.telegram.maxSize

class TelegramUserInfoAdapter(
    val rawUser: User,
    private val sender: AbsSender,
    private val bot: TelegramBot,
) : MultiBotUserInfo {

    override val firstName
        get() = rawUser.firstName

    override val lastName
        get() = rawUser.lastName

    override val nickName
        get() = rawUser.userName

    override val imageUrl: String? by lazy {
        val photo = sender.execute(GetUserProfilePhotos(rawUser.id, 0, 1)).photos.firstOrNull() ?: return@lazy null
        return@lazy sender.execute(GetFile(photo.maxSize()?.fileId ?: return@lazy null)).getFileUrl(bot.botToken)
    }

}