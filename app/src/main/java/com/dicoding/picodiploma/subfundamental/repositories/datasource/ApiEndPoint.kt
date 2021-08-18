package com.dicoding.picodiploma.subfundamental.repositories.datasource


import com.dicoding.picodiploma.subfundamental.BuildConfig
import com.dicoding.picodiploma.subfundamental.repositories.models.User
import com.dicoding.picodiploma.subfundamental.repositories.models.UserDetailResponse
import com.dicoding.picodiploma.subfundamental.repositories.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.TOKEN_API}")
    fun getUsers(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.TOKEN_API}")
    fun getUserDetail(@Path("username") username: String): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.TOKEN_API}")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.TOKEN_API}")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<User>>
}