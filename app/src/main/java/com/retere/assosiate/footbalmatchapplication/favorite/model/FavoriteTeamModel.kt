package com.retere.assosiate.footbalmatchapplication.favorite.model

class FavoriteTeamModel(val id:Long?,
                        val idTeam :String?,
                        val teamName:String?,
                        val teamImage:String?
) {

    companion object {
        const val TABLE_FAVORITE_TEAM= "TABLE_FAVORITE_TEAM"
        const val ID = "ID_"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME= "TEAM_NAME"
        const val TEAM_IMAGE = "TEAM_IMAGE"
    }
}