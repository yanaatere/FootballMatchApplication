package com.retere.assosiate.footbalmatchapplication.team.model

import com.google.gson.annotations.SerializedName

data class TeamsItem(
    @SerializedName("idTeam")
    val idTeam: String? = null,
    @SerializedName("strTeam")
    val strTeam: String? = null,
    @SerializedName("strTeamBadge")
    val strTeamBadge: String? = null
)