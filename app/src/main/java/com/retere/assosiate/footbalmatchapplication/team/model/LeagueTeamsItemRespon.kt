package com.retere.assosiate.footbalmatchapplication.team.model

import com.google.gson.annotations.SerializedName

class LeagueTeamsItemRespon(
    @SerializedName("teams")
    var teams: List<TeamsItem>? = null
)