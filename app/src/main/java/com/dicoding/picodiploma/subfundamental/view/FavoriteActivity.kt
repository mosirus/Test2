package com.dicoding.picodiploma.subfundamental.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.subfundamental.R
import com.dicoding.picodiploma.subfundamental.adapters.ListUserAdapter
import com.dicoding.picodiploma.subfundamental.databinding.ActivityFavoriteBinding
import com.dicoding.picodiploma.subfundamental.repositories.models.User
import com.dicoding.picodiploma.subfundamental.viewModel.DetailViewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var userAdapter: ListUserAdapter
    private lateinit var favoriteViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.userFavorite)

        binding.navView.selectedItemId = R.id.navigation_favorite
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0,0)
                    true
                }
                R.id.navigation_favorite -> {
                    true
                }
                else -> false
            }
        }


        userAdapter = ListUserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = userAdapter

        favoriteViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        favoriteViewModel.getFavoriteUser()?.observe(this,{
            if (it != null ){
                val favList = mapListToArrayList(it)
                userAdapter.setData(favList)
                showLottie(false)
                if (it.isEmpty()) {
                    showLottie(true)
                }
            }
        })

    }

    private fun mapListToArrayList(favUser: List<User>) : ArrayList<User> {
        val listFavUser = ArrayList<User>()
        for(user in favUser) {
            val favUserMapped = User(
                    user.id,
                    user.login,
                    user.avatar_url
            )
            listFavUser.add(favUserMapped)
        }
        return listFavUser
    }

    private fun showLottie(state: Boolean) {
        if (state) {
            binding.apply {
                rvFavorite.visibility = View.GONE
                layoutError.visibility = View.VISIBLE
                lottieNoData.playAnimation()
                lottieNoData.loop(true)
            }
        } else {
            binding.apply {
                rvFavorite.visibility = View.VISIBLE
                binding.layoutError.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting_menu -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}