package com.womensafety.womensafety.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.womensafety.womensafety.R
import com.womensafety.womensafety.util.SessionManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sessionManager = SessionManager(this)
        sharedPreferences =
            this.getSharedPreferences(sessionManager.PREF_NAME, sessionManager.PRIVATE_MODE)
        btnSignout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            sessionManager.setLogin(false)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
            finish()
        }

    }
}