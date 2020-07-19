package com.womensafety.womensafety.fragment.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_create_account_two.view.*

class CreateAccountTwoFragment : Fragment() {

    lateinit var gender: String
    private val sharedPref = activity?.getSharedPreferences(
        getString(R.string.user_preferences),
        Context.MODE_PRIVATE
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_two, container, false)

        val day = view.agePicker.dayOfMonth
        val month = view.agePicker.month
        val year = view.agePicker.year
        val date: String = "$day/$month/$year"

        val male = view.radioMale
        val female = view.radioFemale
        val other = view.radioOther

        when {
            male.isChecked -> {
                gender = male.text.toString()
            }
            female.isChecked -> {
                gender = female.text.toString()
            }
            other.isChecked -> {
                gender = female.text.toString()
            }
        }

        view.btnNextThree.setOnClickListener {
            if (male.isChecked || female.isChecked || other.isChecked) {
                savePref(gender, date)
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.container,
                        CreateAccountThreeFragment()
                    )?.commit()
            } else {
                Toast.makeText(context, "Please select your gender!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        view.btnLoginThree.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    LoginFragment()
                )?.commit()
        }

        view.create2_back_button.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    CreateAccountFragment()
                )?.commit()
        }
        return view
    }

    private fun savePref(gender: String, dob: String) {
        sharedPref?.edit()?.putString("gender", gender)?.apply()
        sharedPref?.edit()?.putString("age", dob)?.apply()
    }
}