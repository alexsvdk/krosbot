package com.github.alexsvdk.krosbot.vk.multibot

import com.github.alexsvdk.krosbot.core.message.DataMessageComponent
import com.github.alexsvdk.krosbot.core.message.IncomingData
import com.github.alexsvdk.krosbot.core.message.IncomingMultiSizeMedia
import com.github.alexsvdk.krosbot.core.message.MultiSizeMediaMessageComponent
import com.vk.api.sdk.objects.photos.Photo
import com.vk.api.sdk.objects.photos.PhotoSizes

class VkIncomingPhoto(
    val photo: Photo
) : IncomingMultiSizeMedia {

    class VkIncomingPhoto(val rawSize: PhotoSizes) :
        MultiSizeMediaMessageComponent.Size(rawSize.width, rawSize.height)

    override val sizes by lazy { photo.sizes.map { VkIncomingPhoto(it) } }

    override fun getData(size: MultiSizeMediaMessageComponent.Size): DataMessageComponent {
        assert(size is VkIncomingPhoto) { "Vk bot can process only VkIncomingPhoto" }
        return IncomingData.fromUrl((size as VkIncomingPhoto).rawSize.src.toURL(), DataMessageComponent.Type.IMAGE)
    }

}
