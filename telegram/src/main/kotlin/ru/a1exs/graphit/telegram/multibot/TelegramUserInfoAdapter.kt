package ru.a1exs.graphit.telegram.multibot

import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.generics.TelegramBot
import ru.a1exs.graphit.multibot.MultiBotUserInfo
import ru.a1exs.graphit.telegram.maxSize

class TelegramUserInfoAdapter(
    val user: User,
    private val sender: AbsSender,
    private val bot: TelegramBot,
) : MultiBotUserInfo {

    override val firstName
        get() = user.firstName

    override val lastName
        get() = user.lastName

    override val nickName
        get() = user.userName

    override val imageUrl: String? by lazy {
        val photo = sender.execute(GetUserProfilePhotos(user.id, 0, 1)).photos.firstOrNull() ?: return@lazy null
        return@lazy sender.execute(GetFile(photo.maxSize()?.fileId ?: return@lazy null)).getFileUrl(bot.botToken)
    }

}