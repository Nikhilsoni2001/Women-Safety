package com.womensafety.womensafety.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.WelcomeScreenFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                WelcomeScreenFragment()
            ).commit()
    }
}