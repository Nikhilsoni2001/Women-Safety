package com.womensafety.womensafety.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.fragment.login.LoginFragment
import com.womensafety.womensafety.util.Validations
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_create_account.view.*

class CreateAccountFragment : Fragment() {

    private val sharedPref = activity?.getSharedPreferences(
        getString(R.string.user_preferences),
        Context.MODE_PRIVATE
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account, container, false)


        view.btnNextOne.setOnClickListener {
            callNextSignUpScreen()
        }

        view.btnLoginOne.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, LoginFragment())?.commit()
        }

        view.create1_back_button.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, WelcomeScreenFragment())
                ?.commit()
        }

        return view
    }

    private fun callNextSignUpScreen() {
        if (Validations.validateFullNameLength(etFullName.text.toString().trim())) {
            FullName.error = null
            if (Validations.validateUserNameLength(etUsername.text.toString().trim())) {
                Username.error = null
                if (Validations.validateEmail(etEmail.text.toString().trim())) {
                    Email.error = null
                    if (Validations.validatePasswordLength(etPassword.text.toString().trim())) {
                        Password.error = null
//                        continue

                        val name = etFullName.text.toString().trim()
                        val user = etUsername.text.toString().trim()
                        val email = etEmail.text.toString().trim()
                        val password = etPassword.text.toString().trim()
                        savePref(name, user, email, password)
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container, CreateAccountTwoFragment())?.commit()
                    } else {
                        Password.error = "Password is too Short!!"
                    }
                } else {
                    Email.error = "Email is Invalid!!"
                }
            } else {
                Username.error = "Username is too Short!!"
            }
        } else {
            FullName.error = "Name is too Short!!"
        }
    }

    private fun savePref(fullName: String, userName: String, email: String, password: String) {
        sharedPref?.edit()?.putString("fullname", fullName)?.apply()
        sharedPref?.edit()?.putString("username", userName)?.apply()
        sharedPref?.edit()?.putString("email", email)?.apply()
        sharedPref?.edit()?.putString("password", password)?.apply()
    }
}