package com.womensafety.womensafety.fragment.forget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import kotlinx.android.synthetic.main.fragment_forget_mobile.*
import kotlinx.android.synthetic.main.fragment_forget_mobile.view.*

class ForgetMobileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_forget_mobile, container, false)

        view.btnVerifyCode.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, PasswordUpdatedFragment())
                ?.commit()
        }
        return view

    }
}

