package com.inzynierkaApp.app.domain.entities.film.playlistinfo.infoCosmetics

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.ImagesCosmeticsEntity
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.TypeValueEntity

data class InfoCosmeticsEntity(val data: DetailInfoOfNewItemsEntity)

data class DetailInfoOfNewItemsEntity(
    val id: String,
    val name: String?,
    val type: TypeValueEntity,
    val images: ImagesCosmeticsEntity,
    val gameplayTags: List<String>?,
    val rarity: ItemRarityEntity?,
    val set: ItemSetEntity?,
    val introduction: ItemIntroductionEntity?,
    val added: String?,
    val description: String?
)

data class ItemRarityEntity(val value: String?, val displayValue: String?)

data class ItemSetEntity(val value: String?, val text: String?)

data class ItemIntroductionEntity(val chapter: String?, val season: String? ,val text: String?)