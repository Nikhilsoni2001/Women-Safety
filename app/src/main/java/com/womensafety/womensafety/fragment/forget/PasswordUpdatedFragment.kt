package com.womensafety.womensafety.fragment.forget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_password_updated.view.*

class PasswordUpdatedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_password_updated, container, false)
        view.btnLoginAgain.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, LoginFragment())?.commit()
        }
        return view
    }
}
