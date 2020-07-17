package com.womensafety.womensafety.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import kotlinx.android.synthetic.main.fragment_create_account_two.*
import kotlinx.android.synthetic.main.fragment_create_account_two.view.*

class CreateAccountTwoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_two, container, false)

        view.btnNext.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, CreateAccountThreeFragment())?.commit()
        }
        return view
    }
}