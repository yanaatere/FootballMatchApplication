package com.retere.assosiate.footbalmatchapplication.detail.team.model


import com.google.gson.annotations.SerializedName


data class TeamItemResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
)