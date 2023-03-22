package com.inzynierkaApp.app.domain.entities.film.playlistinfo.news

data class NewsEntity(val data: SpecificNewsEntity)

data class SpecificNewsEntity(
    val br: BattleRoyaleNewsEntity,
    val stw: SaveTheWordNewsEntity?
)

data class BattleRoyaleNewsEntity(val motds: List<BrItemsEntity>)

data class BrItemsEntity(
    val id: String,
    val title: String?,
    val tabTitle: String?,
    val image: String?,
    val tileImage: String?,
    val body: String?,
    )

data class SaveTheWordNewsEntity(val messages: List<StwItemsEntity>?)

data class StwItemsEntity(
    val title: String?,
    val body: String?,
    val image: String?,
)

fun mapStwToBr(messages: List<StwItemsEntity>): List<BrItemsEntity> {
    return messages.map {
        BrItemsEntity(
            tabTitle = it.title,
            image = it.image,
            body = it.body,
            id = "",
            tileImage = "",
            title = ""
        )
    }
}