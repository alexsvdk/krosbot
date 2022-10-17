package com.github.alexsvdk.krosbot.core.message

interface PollMessageComponent{

    open class VoteData(
        val optionName: String,
    )

    val voteData: List<VoteData>
    val isAnonymous: Boolean
    val question: String?

}