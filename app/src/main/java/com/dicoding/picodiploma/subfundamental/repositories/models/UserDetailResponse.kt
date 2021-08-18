package com.dicoding.picodiploma.subfundamental.repositories.models


data class UserDetailResponse(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val name: String,
    val company: String,
    val location: String,
    val bio: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val html_url: String
)