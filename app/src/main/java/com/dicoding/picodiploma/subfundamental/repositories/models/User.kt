package com.dicoding.picodiploma.subfundamental.repositories.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class User (
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatar_url: String
) : Serializable