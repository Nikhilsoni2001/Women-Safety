package com.womensafety.womensafety.fragment.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.womensafety.womensafety.R
import com.womensafety.womensafety.activity.HomeActivity
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.fragment.forget.MakeSelectionFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountFragment
import com.womensafety.womensafety.util.ConnectionManager
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btnLogin.setOnClickListener {
            val codePicker =view.countryCodeHolderLogin.fullNumber
            val mobile = view.etMobileNumberLogin.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val mobileNumber="+$codePicker$mobile"
            if (ConnectionManager().isNetworkAvailable(context)) {

                val checkUser: Query =
                    FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo")
                        .equalTo(mobileNumber)

                checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            view.MobileLogin.error = null
                            view.MobileLogin.isErrorEnabled = false

                            val systemPassword: String? =
                                snapshot.child(mobileNumber).child("password")
                                    .getValue(String::class.java)

                            if (systemPassword.equals(password)) {
                                view.Password.error = null
                                view.Password.isErrorEnabled = false
                                val fullname = snapshot.child(mobileNumber).child("fullName")
                                    .getValue(String::class.java)
                                val email =
                                    snapshot.child(mobileNumber).child("email")
                                        .getValue(String::class.java)
                                val phone = snapshot.child(mobileNumber).child("phoneNo")
                                    .getValue(String::class.java)
                                val date =
                                    snapshot.child(mobileNumber).child("date")
                                        .getValue(String::class.java)
                                context.let {
                                    startActivity(Intent(it, HomeActivity::class.java))
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Password does not match!!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } else {
                            Toast.makeText(context, "Data does not Exists!!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                })
            } else {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Please connect internet to proceed further!!")
                    .setCancelable(false).setPositiveButton("Connect") { _, _ ->
                        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }.setNegativeButton("cancel") { _, _ ->
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container, WelcomeScreenFragment())?.commit()
                    }
                builder.create().show()
                activity?.finish()
            }
        }

        view.btnCreateAccountLogin.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, CreateAccountFragment())?.commit()
        }

        view.btnForgetLogin.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, MakeSelectionFragment())?.commit()
        }

        view.login_back.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, WelcomeScreenFragment())?.commit()
        }
        return view
    }
}