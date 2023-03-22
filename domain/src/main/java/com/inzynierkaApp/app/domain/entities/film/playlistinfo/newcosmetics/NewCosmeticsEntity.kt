package com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics

data class NewCosmeticsEntity(val data: NewItemsEntity)

data class NewItemsEntity(val items: List<ListOfNewItemsEntity>)

data class ListOfNewItemsEntity(
    val id: String,
    val name: String?,
    val type: TypeValueEntity,
    val images: ImagesCosmeticsEntity,
    var count: Int,
    val gameplayTags: List<String>?
)

data class ImagesCosmeticsEntity(val icon: String?, val smallIcon: String?)

data class TypeValueEntity(val rarity: String?, val displayValue: String?)


