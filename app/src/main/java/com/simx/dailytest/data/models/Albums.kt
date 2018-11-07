package com.simx.dailytest.data.models

import com.google.gson.annotations.SerializedName

data class Albums(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)