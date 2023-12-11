package com.example.geotag.data.response

data class SignUpResponse(
	val message: String,
	val data: UserData
)

data class UserData(
	val uuid: String,
	val name: String,
	val email: String,
	val role: String
)
