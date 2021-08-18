package com.dicoding.picodiploma.subfundamental.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.subfundamental.repositories.datasource.Retrofit
import com.dicoding.picodiploma.subfundamental.repositories.localdata.FavoriteUserDao
import com.dicoding.picodiploma.subfundamental.repositories.localdata.UserRoomDatabase
import com.dicoding.picodiploma.subfundamental.repositories.models.User
import com.dicoding.picodiploma.subfundamental.repositories.models.UserDetailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val detailUser = MutableLiveData<UserDetailResponse>()
    val listFollowersUser = MutableLiveData<ArrayList<User>>()
    val listFollowingUser = MutableLiveData<ArrayList<User>>()

    private var userFavDao: FavoriteUserDao?
    private var userFavDb: UserRoomDatabase? = UserRoomDatabase.getDb(application)

    init {
        userFavDao = userFavDb?.favoriteUserDao()
    }

    fun setDetailUser(username: String) {
        Retrofit.apiRequest.getUserDetail(username).enqueue(object : Callback<UserDetailResponse> {

            override fun onResponse(call: Call<UserDetailResponse>, response: Response<UserDetailResponse>
            ) {
                if (response.isSuccessful) {
                    detailUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getDetailUser(): LiveData<UserDetailResponse> {
        return detailUser
    }

    fun setFollowersUser(username: String) {
        Retrofit.apiRequest.getFollowers(username).enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    listFollowersUser.postValue((response.body()))
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getFollowersUser(): LiveData<ArrayList<User>> {
        return listFollowersUser
    }

    fun setFollowingUser(username: String) {
        Retrofit.apiRequest.getFollowing(username).enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    listFollowingUser.postValue((response.body()))
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getFollowingUser(): LiveData<ArrayList<User>> {
        return listFollowingUser
    }

    fun addUserToFav(id: Int, username: String, avatarUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userFav = User(
                id,
                username,
                avatarUrl
            )
            userFavDao?.addUserToFavorite(userFav)
        }
    }

    suspend fun checkUserFavId(id: Int) = userFavDao?.checkUserId(id)

    fun deleteFromFav(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userFavDao?.removeUserFavorite(id)
        }
    }

    fun getFavoriteUser(): LiveData<List<User>>? {
        return userFavDao?.getFavUser()
    }
}