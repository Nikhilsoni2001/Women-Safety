package com.womensafety.womensafety.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountFragment
import kotlinx.android.synthetic.main.fragment_welcome_screen.view.*


class WelcomeScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome_screen, container, false)

        view.btnLoginWelcome.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, LoginFragment())?.commit()
        }

        view.btnRegisterWelcome.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, CreateAccountFragment())
                ?.commit()
        }

        return view
    }
}