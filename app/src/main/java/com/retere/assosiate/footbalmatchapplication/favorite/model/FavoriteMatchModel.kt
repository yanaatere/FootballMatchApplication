package com.retere.assosiate.footbalmatchapplication.favorite.model

//Table Untuk Favorite Match
class FavoriteMatchModel(
    val id: Long?,
    val matchId: String?,
    val matchDate: String?,
    val strHomeName: String?,
    val strAwayName: String?,
    val strScoreHome: String?,
    val strScoreAway: String?
) {

    companion object {
        const val TABLE_FAVORITE_MATCH = "tbl_favorite_match"
        const val ID = "ID_"
        const val MATCH_ID = "MATCH_ID"
        const val MATCH_DATE = "MATCH_DATE"
        const val HOME_TEAM = "HOME_TEAM"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val SCORE_HOME = "SCORE_HOME_TEAM"
        const val SCORE_AWAY = "SCORE_AWAY_TEAM"
    }
}