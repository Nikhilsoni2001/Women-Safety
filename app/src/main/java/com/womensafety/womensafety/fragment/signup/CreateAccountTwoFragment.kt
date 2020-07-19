package com.womensafety.womensafety.fragment.signup

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

    private lateinit var gender: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_account_two, container, false)

        val day = view.agePicker.dayOfMonth
        val month = view.agePicker.month
        val year = view.agePicker.year
        val date = "$day/$month/$year"

        val male = view.radioMale
        val female = view.radioFemale
        val other = view.radioOther

        view.rbGender.setOnCheckedChangeListener { _, _ ->
            when {
                male.isChecked -> {
                    gender = male.text.toString()
                }
                female.isChecked -> {
                    gender = female.text.toString()
                }
                other.isChecked -> {
                    gender = other.text.toString()
                }
            }
        }

        val data = this.arguments
        val fullName = data?.getString("fullName")
        val userName = data?.getString("userName")
        val email = data?.getString("email")
        val password = data?.getString("password")


        view.btnNextThree.setOnClickListener {
            if (male.isChecked || female.isChecked || other.isChecked) {

                val frag = CreateAccountThreeFragment()
                val bundle = Bundle()
                bundle.putString("fullName", fullName)
                bundle.putString("userName", userName)
                bundle.putString("email", email)
                bundle.putString("password", password)
                bundle.putString("gender", gender)
                bundle.putString("dob", date)
                frag.arguments = bundle

                fragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.commit()
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
}