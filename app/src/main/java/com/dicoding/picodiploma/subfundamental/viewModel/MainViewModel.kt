package com.dicoding.picodiploma.subfundamental.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.subfundamental.repositories.datasource.Retrofit
import com.dicoding.picodiploma.subfundamental.repositories.models.User
import com.dicoding.picodiploma.subfundamental.repositories.models.UserResponse
import com.dicoding.picodiploma.subfundamental.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(name: String){
        Retrofit.apiRequest.getUsers(name).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body()?.items)
                    Log.d(TAG, response.body()?.items.toString())
                } else {
                    Log.d(TAG, response.code().toString())
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Fail To Fetching", t.message.toString())
            }

        })
    }

    fun getSearchUser() : LiveData<ArrayList<User>> {
        return listUser
    }
}