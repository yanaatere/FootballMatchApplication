package com.retere.assosiate.footbalmatchapplication.detail.team.model


import com.google.gson.annotations.SerializedName


data class PlayersItemResponse(

    @field:SerializedName("players")
    val players: List<PlayersItem>
)