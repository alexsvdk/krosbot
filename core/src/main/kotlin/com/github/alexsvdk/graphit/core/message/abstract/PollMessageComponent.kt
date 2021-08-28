package com.github.alexsvdk.graphit.core.message

interface PollMessageComponent{

    open class VoteData(
        val optionName: String,
    )

    val voteData: List<VoteData>
    val isAnonymous: Boolean
    val question: String?

}