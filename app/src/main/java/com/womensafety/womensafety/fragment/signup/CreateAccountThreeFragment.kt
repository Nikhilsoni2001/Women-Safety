package com.womensafety.womensafety.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.OtpFragment
import com.womensafety.womensafety.fragment.login.LoginFragment
import com.womensafety.womensafety.util.Validations
import kotlinx.android.synthetic.main.fragment_create_account.view.*
import kotlinx.android.synthetic.main.fragment_create_account_three.*


class  CreateAccountThreeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_three, container, false)

        view.btnNext.setOnClickListener {
//            if (Validations.validateMobile(etMobile.text.toString().trim())) {
//                MOBILE.error = null
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.container,
                        OtpFragment()
                    )?.commit()
//            } else {
//                MOBILE.error = "Invalid Mobile Number!!"
//            }

        }

        view.btnLogin.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    LoginFragment()
                )?.commit()
        }
        return view
    }
}