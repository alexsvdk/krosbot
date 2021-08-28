package com.github.alexsvdk.graphit.core.message


interface MultiSizeMediaMessageComponent: MessageComponent {

    open class Size(
        val width: Int,
        val height: Int,
    )

    val sizes: List<Size>

    fun getData(size: Size): DataMessageComponent

    operator fun get(size: Size): DataMessageComponent {
        val data = getData(size)
        assert(data.type in listOf(
            DataMessageComponent.Type.IMAGE,
            DataMessageComponent.Type.VIDEO,
        ))
        return data
    }

}