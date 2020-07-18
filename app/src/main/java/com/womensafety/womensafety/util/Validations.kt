package com.womensafety.womensafety.util

import android.util.Patterns

object Validations {

    fun validateMobile(mobile: String): Boolean {
        return mobile.length == 10
    }

    fun validatePasswordLength(password: String): Boolean {
        return password.length >= 4
    }

    fun validateFullNameLength(name: String): Boolean {
        return name.length >= 3
    }
    fun validateUserNameLength(Username: String): Boolean {
        return Username.length >= 4
    }

    fun matchPassword(pass: String, confirmPass: String): Boolean {
        return pass == confirmPass
    }

    fun validateEmail(email: String): Boolean {
        return (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}