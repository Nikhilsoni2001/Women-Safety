package com.womensafety.womensafety.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.util.SessionManager
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sessionManager = SessionManager(this)

        val sideAnim: Animation =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.side_anim)
        val bottomAnim: Animation =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.bottom_anim)
        logo?.animation = sideAnim
        logo?.animation = bottomAnim
        logo_name?.animation = sideAnim
        logo_name?.animation = bottomAnim

        Handler(Looper.getMainLooper()).postDelayed({
            openNewActivity()
        }, 2000)

    }

    private fun openNewActivity() {
        if (sessionManager.isLoggedIn()) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeScreenFragment()).commit()
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}