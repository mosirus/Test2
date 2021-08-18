package com.dicoding.picodiploma.consumerapp.database

import android.database.Cursor
import com.dicoding.picodiploma.consumerapp.model.User

object MappingCursor {
    fun mapCursorToArrayList(cursor: Cursor?) : ArrayList<User> {
        val listUsers = ArrayList<User>()

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContractFavUser.FavoriteUserColumns.USER_ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractFavUser.FavoriteUserColumns.USER_USERNAME))
                val urlAvatar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContractFavUser.FavoriteUserColumns.USER_AVATAR_URL))

                listUsers.add(
                    User(id,username,urlAvatar)
                )
            }
        }
        return listUsers
    }
}