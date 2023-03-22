package com.inzynierkaApp.app.domain.entities.film.playlistinfo.account

data class AccountInfoEntity(val data: InfoEntity)

data class InfoEntity(
    val account: SpecificInfoEntity,
    val battlePass: BattlePassInfoEntity,
    val image: String?,
    val stats: AllStatisticsEntity
    )

data class AllStatisticsEntity(val all: StatisticsEntity)

data class SpecificInfoEntity(val id: String?, val name: String?)

data class BattlePassInfoEntity(val level: Int?, val progress: Int?)

data class StatisticsEntity(val overall: OverallStatisticEntity)

data class OverallStatisticEntity(
    val score: Int?,
    val scorePerMin: Double?,
    val scorePerMatch: Double?,
    val wins: Int?,
    val top3: Int?,
    val top5: Int?,
    val top6: Int?,
    val top10: Int?,
    val kills: Int?,
    val killsPerMin: Double?,
    val killsPerMatch: Double?,
    val deaths: Int?,
    val kd: Double?,
    val matches: Int?,
    val winRate: Double?,
    val minutesPlayed: Int?,
)
