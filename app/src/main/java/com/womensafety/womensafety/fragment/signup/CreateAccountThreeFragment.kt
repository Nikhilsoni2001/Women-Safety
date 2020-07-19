package com.womensafety.womensafety.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hbb20.CountryCodePicker
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.OtpFragment
import com.womensafety.womensafety.fragment.login.LoginFragment
import com.womensafety.womensafety.util.Validations
import kotlinx.android.synthetic.main.fragment_create_account_three.*
import kotlinx.android.synthetic.main.fragment_create_account_three.view.*


class  CreateAccountThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_three, container, false)

        val data = this.arguments
        val fullName = data?.getString("fullName")
        val userName = data?.getString("userName")
        val email = data?.getString("email")
        val password = data?.getString("password")
        val gender = data?.getString("gender")
        val dob = data?.getString("dob")

        view.btnNextTwo.setOnClickListener {
            if (Validations.validateMobile(etMobile.text.toString().trim())) {
                MOBILE.error = null
                val countryCodePicker = view.countryCodeHolder.selectedCountryCode
                val mobile = view.etMobile.text.toString().trim()

                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.container,
                        OtpFragment()
                    )?.commit()
            } else {
                MOBILE.error = "Incorrect Mobile Number!!"
            }
        }

        view.btnLoginTwo.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    LoginFragment()
                )?.commit()
        }

        view.create3_back_button.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    CreateAccountTwoFragment()
                )?.commit()
        }
        return view
    }
}