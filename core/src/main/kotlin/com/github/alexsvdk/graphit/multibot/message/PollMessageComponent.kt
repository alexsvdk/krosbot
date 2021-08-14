package com.github.alexsvdk.graphit.multibot.message

data class VoteData(
    val optionName: String,
    val voteCount: Int = 0,
)

data class PollMessageComponent(
    val voteData: List<VoteData>,
    val isClosed: Boolean = false,
    val isAnonymous: Boolean = false,
    val question: String? = null
) : MessageComponent