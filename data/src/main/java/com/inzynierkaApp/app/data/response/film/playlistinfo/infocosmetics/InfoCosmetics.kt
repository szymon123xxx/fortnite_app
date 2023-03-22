package com.inzynierkaApp.app.data.response.film.playlistinfo.infocosmetics

import com.inzynierkaApp.app.data.response.film.playlistinfo.newcosmetics.ImagesCosmetics
import com.inzynierkaApp.app.data.response.film.playlistinfo.newcosmetics.TypeValue
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics.*

data class InfoCosmetics(val data: DetailInfoOfNewItems) {
    fun toEntity(): InfoCosmeticsEntity {
        return InfoCosmeticsEntity(data.toEntity())
    }
}

data class DetailInfoOfNewItems(
    val id: String,
    val name: String?,
    val type: TypeValue,
    val images: ImagesCosmetics,
    val gameplayTags: List<String>?,
    val rarity: ItemRarity?,
    val set: ItemSet?,
    val introduction: ItemIntroduction?,
    val added: String?,
    val description: String?
) {
    fun toEntity(): DetailInfoOfNewItemsEntity {
        return DetailInfoOfNewItemsEntity(
            id,
            name,
            type.toEntity(),
            images.toEntity(),
            gameplayTags,
            rarity?.toEntity(),
            set?.toEntity(),
            introduction?.toEntity(),
            added,
            description
        )
    }
}

data class ItemRarity(val value: String?, val displayValue: String?) {
    fun toEntity(): ItemRarityEntity {
        return ItemRarityEntity(value, displayValue)
    }
}

data class ItemSet(val value: String?, val text: String?) {
    fun toEntity(): ItemSetEntity {
        return ItemSetEntity(value, text)
    }
}

data class ItemIntroduction(val chapter: String?, val season: String?, val text: String?) {
    fun toEntity(): ItemIntroductionEntity {
        return ItemIntroductionEntity(chapter, season, text)
    }
}

