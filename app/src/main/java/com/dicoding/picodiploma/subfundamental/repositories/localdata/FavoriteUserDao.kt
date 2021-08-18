package com.dicoding.picodiploma.subfundamental.repositories.localdata

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dicoding.picodiploma.subfundamental.repositories.models.User

@Dao
interface FavoriteUserDao {

    @Insert
    suspend fun addUserToFavorite(favoriteUser: User)

    @Query("SELECT * FROM favorite_user")
    fun getFavUser(): LiveData<List<User>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUserId(id: Int) : Int

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeUserFavorite(id: Int) : Int

    @Query("SELECT * FROM favorite_user")
    fun getFavUserProvider(): Cursor

}