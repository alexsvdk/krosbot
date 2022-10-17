package com.github.alexsvdk.krosbot.core

interface ChatInfo {

    val id: String
    val isGroup: Boolean
    val name: String?
    val members: List<UserInfo>?

}