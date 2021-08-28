package com.github.alexsvdk.graphit.core

interface ChatInfo {

    val id: String
    val isGroup: Boolean
    val name: String?
    val members: List<UserInfo>?

}