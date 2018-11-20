package com.retere.assosiate.footbalmatchapplication.detail.team.model

import com.google.gson.annotations.SerializedName

data class PlayersItemDetailResponse(
    @SerializedName("players")
    val players: List<PlayersItemDetail>
)