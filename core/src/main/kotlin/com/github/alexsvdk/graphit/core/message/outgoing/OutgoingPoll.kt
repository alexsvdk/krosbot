package com.github.alexsvdk.graphit.core.message

open class OutgoingPoll(
    override val question: String?,
    override val voteData: List<PollMessageComponent.VoteData>,
    override val isAnonymous: Boolean,
) : PollMessageComponent, OutgoingMessageComponent