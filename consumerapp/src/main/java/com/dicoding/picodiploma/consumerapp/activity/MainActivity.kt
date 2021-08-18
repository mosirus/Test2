package com.dicoding.picodiploma.consumerapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.consumerapp.R
import com.dicoding.picodiploma.consumerapp.adapter.ListUserAdapter
import com.dicoding.picodiploma.consumerapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.consumerapp.viewModel.FavoriteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: ListUserAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        userAdapter = ListUserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = userAdapter

        favoriteViewModel.setFavoriteUser(this)

        favoriteViewModel.getFavoriteUser()?.observe(this,{
            if (it != null ){
                userAdapter.setData(it)
                showLottie(false)
                if (it.isEmpty()) {
                    showLottie(true)
                }
            }
        })

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
}