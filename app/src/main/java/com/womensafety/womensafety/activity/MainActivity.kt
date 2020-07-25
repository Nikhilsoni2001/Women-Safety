package com.womensafety.womensafety.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.OtpFragment
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.fragment.forget.ForgetMobileFragment
import com.womensafety.womensafety.fragment.forget.NewPasswordFragment
import com.womensafety.womensafety.fragment.forget.PasswordUpdatedFragment
import com.womensafety.womensafety.fragment.login.LoginFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountThreeFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountTwoFragment

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

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.container)

        when (frag) {
            is OtpFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeScreenFragment()).commit()
            }
            is LoginFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeScreenFragment()).commit()
            }
            is CreateAccountFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeScreenFragment()).commit()
            }
            is CreateAccountTwoFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CreateAccountFragment()).commit()
            }
            is CreateAccountThreeFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CreateAccountTwoFragment()).commit()
            }
            is ForgetMobileFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment()).commit()
            }
            is NewPasswordFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeScreenFragment()).commit()
            }
            is PasswordUpdatedFragment -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeScreenFragment()).commit()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}