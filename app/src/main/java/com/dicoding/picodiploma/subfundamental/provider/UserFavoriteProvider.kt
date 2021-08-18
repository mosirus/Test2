package com.dicoding.picodiploma.subfundamental.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.picodiploma.subfundamental.repositories.localdata.FavoriteUserDao
import com.dicoding.picodiploma.subfundamental.repositories.localdata.UserRoomDatabase
import kotlinx.coroutines.*

class UserFavoriteProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.dicoding.picodiploma.subfundamental"
        private const val TABLE_NAME = "favorite_user"
        const val ID_FAVORITE_USER_DATA = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_FAVORITE_USER_DATA)
        }
    }

    private lateinit var userDao: FavoriteUserDao

    override fun onCreate(): Boolean {
        userDao = context?.let {
            UserRoomDatabase.getDb(it)?.favoriteUserDao() }!!
        return false
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }


    override fun query(
            uri: Uri, projection: Array<String>?, selection: String?,
            selectionArgs: Array<String>?, sortOrder: String?,
    ): Cursor? {
        return runBlocking { getCursor(uri) }
    }

    private suspend fun getCursor(uri: Uri): Cursor? {
        lateinit var cursor: Cursor
        val process = GlobalScope.async {
            val dispatcher = this.coroutineContext
            CoroutineScope(dispatcher).launch {
                when(uriMatcher.match(uri)) {
                    ID_FAVORITE_USER_DATA -> {
                        cursor = userDao.getFavUserProvider()
                        if (context != null) {
                            cursor!!.setNotificationUri(context?.contentResolver, uri)
                        }
                    }
                    else -> { cursor == null }
                }
            }
        }
        process.await()
        return cursor!!
    }

    override fun update(
            uri: Uri, values: ContentValues?, selection: String?,
            selectionArgs: Array<String>?,
    ): Int {
        return 0
    }
}