package com.retere.assosiate.footbalmatchapplication.search.team.model

import com.google.gson.annotations.SerializedName

class SearchTeamsItemResponse(
    @SerializedName("teams")
    val teams: List<SearchTeamsItem>
)