package com.dicoding.picodiploma.consumerapp.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.consumerapp.database.DatabaseContractFavUser
import com.dicoding.picodiploma.consumerapp.database.MappingCursor
import com.dicoding.picodiploma.consumerapp.model.User

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var listFavorite = MutableLiveData<ArrayList<User>>()

    fun setFavoriteUser(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContractFavUser.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null,
        )
        val listConvertToArrayList = MappingCursor.mapCursorToArrayList(cursor)
        listFavorite.postValue(listConvertToArrayList)
    }

    fun getFavoriteUser(): LiveData<ArrayList<User>> {
        return listFavorite
    }
}