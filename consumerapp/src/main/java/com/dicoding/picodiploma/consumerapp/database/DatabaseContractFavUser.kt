package com.dicoding.picodiploma.consumerapp.database

import android.net.Uri
import android.provider.BaseColumns

const val AUTHORITY = "com.dicoding.picodiploma.subfundamental"
const val SCHEME = "content"

class DatabaseContractFavUser {

    object FavoriteUserColumns: BaseColumns {
        private const val TABLE_NAME = "favorite_user"
        const val USER_ID = "id"
        const val USER_USERNAME = "login"
        const val USER_AVATAR_URL = "avatar_url"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()
    }
}