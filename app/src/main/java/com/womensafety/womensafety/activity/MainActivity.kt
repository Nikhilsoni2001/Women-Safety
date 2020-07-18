package com.womensafety.womensafety.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                LoginFragment()
            ).commit()

    }
}