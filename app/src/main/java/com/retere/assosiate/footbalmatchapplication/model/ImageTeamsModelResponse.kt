package com.retere.assosiate.footbalmatchapplication.model

import com.google.gson.annotations.SerializedName


data class ImageTeamsModelResponse(

	@field:SerializedName("teams")
	val teams: List<ImageTeamsModel>? = null
)