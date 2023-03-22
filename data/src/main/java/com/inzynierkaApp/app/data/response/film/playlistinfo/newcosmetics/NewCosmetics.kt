package com.inzynierkaApp.app.data.response.film.playlistinfo.newcosmetics

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.*

data class NewCosmetics(val data: NewItems) {
    fun toEntity(): NewCosmeticsEntity {
        return NewCosmeticsEntity(data.toEntity() )
    }
}

data class NewItems(val items: List<ListOfNewItems>) {
    fun toEntity(): NewItemsEntity {
        return NewItemsEntity(items.map{ it.toEntity()} )
    }
}

data class ListOfNewItems(
    val id: String,
    val name: String?,
    val type: TypeValue,
    val images: ImagesCosmetics,
    val gameplayTags: List<String>?,
    var count: Int
){
    fun toEntity(): ListOfNewItemsEntity {
        return ListOfNewItemsEntity(id, name, type.toEntity(), images.toEntity(), count, gameplayTags)
    }
}

data class TypeValue(val value: String?, val displayValue: String?) {
    fun toEntity(): TypeValueEntity {
        return TypeValueEntity(value, displayValue)
    }
}

data class ImagesCosmetics(val icon: String?, val smallIcon: String?) {
    fun toEntity(): ImagesCosmeticsEntity {
        return ImagesCosmeticsEntity(icon, smallIcon)
    }
}

