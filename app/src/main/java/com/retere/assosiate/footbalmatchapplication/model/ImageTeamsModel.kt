package com.retere.assosiate.footbalmatchapplication.model


import com.google.gson.annotations.SerializedName

//Untuk Mengambil Logo Team
data class ImageTeamsModel(
	@field:SerializedName("strTeamBadge")
	val strTeamBadge: String? = null
)