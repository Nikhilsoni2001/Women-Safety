package com.womensafety.womensafety.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_create_account.view.*
import kotlinx.android.synthetic.main.fragment_create_account_two.view.btnNext

class CreateAccountTwoFragment : Fragment() {

//    lateinit var gender: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_two, container, false)

//        val day = agePicker.dayOfMonth
//        val month = agePicker.month
//        val year = agePicker.year

//        val radioGroup = rbGender.checkedRadioButtonId
//        val radioButton = view.findViewById<RadioButton>(radioGroup)
//        val date: String = "$day/$month/$year"

//        when {
//            radioMale.isChecked -> {
//                gender = "Male"
//            }
//            radioMale.isChecked -> {
//                gender = "Female"
//            }
//            radioOther.isChecked -> {
//                gender = "Other"
//            }
//            else -> {
//                Toast.makeText(context, "Please select your gender!!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }

        view.btnNext.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    CreateAccountThreeFragment()
                )?.commit()
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