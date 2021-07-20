package ru.a1exs.graphit.multibot

interface MultiBotUserInfo {

    val firstName: String?
    val lastName: String?
    val nickName: String?
    val name: String?
        get() {
            if (firstName == null && lastName == null) return null
            val builder = StringBuilder()
            if (firstName != null) builder.append(firstName)
            if (firstName != null && lastName != null) builder.append(" ")
            if (lastName != null) builder.append(lastName)
            return builder.toString()
        }

    val imageUrl: String?

}