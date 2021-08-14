package com.github.alexsvdk.graphit.nodes.graphit

import com.github.alexsvdk.graphit.state.Id

open class GraphitNodeState(
    override val id: String,
    val nodeId: String,
) : Id