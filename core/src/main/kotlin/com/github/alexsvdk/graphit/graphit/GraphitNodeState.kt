package com.github.alexsvdk.graphit.graphit

import com.github.alexsvdk.graphit.state.Id

open class GraphitNodeState(
    override val id: String,
    val nodeId: String,
) : Id