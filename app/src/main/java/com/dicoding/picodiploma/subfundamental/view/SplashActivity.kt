package com.dicoding.picodiploma.subfundamental.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.dicoding.picodiploma.subfundamental.R
import com.dicoding.picodiploma.subfundamental.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var handler: Handler

    companion object {
        const val TIME_DELAY = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_DELAY.toLong())

        binding.ivLogoSplash.startAnimation(topAnim)
        binding.ivTextSplash.startAnimation(bottomAnim)
        binding.tvSloganSplash.startAnimation(bottomAnim)
    }
}