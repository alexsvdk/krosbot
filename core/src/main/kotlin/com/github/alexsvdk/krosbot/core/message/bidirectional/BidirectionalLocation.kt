package com.github.alexsvdk.krosbot.core.message

typealias OutgoingLocation = BidirectionalLocation
typealias IncomingLocation = BidirectionalLocation

open class BidirectionalLocation(
    override val latitude: Double, override val longitude: Double
) : LocationMessageComponent, BidirectionalMessageComponent