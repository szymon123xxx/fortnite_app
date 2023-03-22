package com.inzynierkaApp.app.data.response.film.playlistinfo.account

import com.inzynierkaApp.app.domain.entities.film.playlistinfo.account.*

data class AccountInfo(val data: Info) {
    fun toEntity(): AccountInfoEntity {
        return AccountInfoEntity(data.toEntity())
    }
}

data class Info(
    val account: SpecificInfo,
    val battlePass: BattlePassInfo,
    val image: String?,
    val stats: AllStatistics
) {
    fun toEntity(): InfoEntity {
        return InfoEntity(account.toEntity(), battlePass.toEntity(), image, stats.toEntity())
    }
}

data class SpecificInfo(val id: String?, val name: String?) {
    fun toEntity(): SpecificInfoEntity {
        return SpecificInfoEntity(id, name)
    }
}

data class BattlePassInfo(val level: Int?, val progress: Int?) {
    fun toEntity(): BattlePassInfoEntity {
        return BattlePassInfoEntity(level, progress)
    }
}

data class AllStatistics(val all: Statistics) {
    fun toEntity(): AllStatisticsEntity {
        return AllStatisticsEntity(all.toEntity())
    }
}

data class Statistics(val overall: OverallStatistic) {
    fun toEntity(): StatisticsEntity {
        return StatisticsEntity(overall.toEntity())
    }
}

data class OverallStatistic(
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
) {
    fun toEntity(): OverallStatisticEntity {
        return OverallStatisticEntity(
            score,
            scorePerMin,
            scorePerMatch,
            wins,
            top3,
            top5,
            top6,
            top10,
            kills,
            killsPerMin,
            killsPerMatch,
            deaths,
            kd,
            matches,
            winRate,
            minutesPlayed
        )
    }
}
