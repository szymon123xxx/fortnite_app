package com.inzynierkaApp.app.data.response.film.playlistinfo.news

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.*

data class News(val data: SpecificNews){
    fun toEntity(): NewsEntity {
        return NewsEntity(data.toEntity())
    }
}

data class SpecificNews(
    val br: BattleRoyaleNews,
    val stw: SaveTheWordNews?
){
    fun toEntity(): SpecificNewsEntity {
        return SpecificNewsEntity(br.toEntity(), stw?.toEntity())
    }
}

data class BattleRoyaleNews(val motds: List<BrItems>){
    fun toEntity(): BattleRoyaleNewsEntity {
        return BattleRoyaleNewsEntity(motds.map { it.toEntity() })
    }
}

data class BrItems(
    val id: String,
    val title: String?,
    val tabTitle: String?,
    val image: String?,
    val tileImage: String?,
    val body: String?,
){
    fun toEntity(): BrItemsEntity {
        return BrItemsEntity(id, title, tabTitle, image, tileImage, body)
    }
}


data class SaveTheWordNews(val messages: List<StwItems>){
    fun toEntity(): SaveTheWordNewsEntity {
        return SaveTheWordNewsEntity(messages.map { it.toEntity() })
    }
}

data class StwItems(
    val title: String?,
    val body: String?,
    val image: String?,
){
    fun toEntity(): StwItemsEntity {
        return StwItemsEntity(title, body, image)
    }
}

