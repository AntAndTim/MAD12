package me.antandtim.mad12.authentication.model

data class User(
    val login: String,
    val password: String?,
    val name: String?,
    val last_name: String?
)