package com.womensafety.womensafety.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.activity.HomeActivity
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.fragment.forget.MakeSelectionFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountFragment
import com.womensafety.womensafety.util.Validations
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btnLogin.setOnClickListener {
            if (Validations.validateUserNameLength(etUsername.text.toString().trim())) {
                Username.error = null
                if (Validations.validatePasswordLength(etPassword.text.toString().trim())) {
                    Password.error = null
                    activity?.let {
                        val intent = Intent(it, HomeActivity::class.java)
                        startActivity(intent)
                    }

                } else {
                    Password.error = "Incorrect Password!!"
                }
            } else {
                Username.error = "Incorrect Username!!"
            }
        }

        view.btnCreateAccountLogin.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, CreateAccountFragment())?.commit()
        }

        view.btnForgetLogin.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, MakeSelectionFragment())?.commit()
        }

        view.login_back.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, WelcomeScreenFragment())?.commit()
        }
        return view
    }
}