package com.womensafety.womensafety.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.forget.MakeSelectionFragment
import kotlinx.android.synthetic.main.fragment_otp.view.*

class OtpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_otp, container, false)
        view.btnVerifyCode.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, MakeSelectionFragment())
                ?.commit()
        }
        return view
    }
}