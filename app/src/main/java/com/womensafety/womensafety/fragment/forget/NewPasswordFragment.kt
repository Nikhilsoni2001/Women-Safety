package com.womensafety.womensafety.fragment.forget

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.WelcomeScreenFragment
import com.womensafety.womensafety.util.ConnectionManager
import com.womensafety.womensafety.util.Validations
import kotlinx.android.synthetic.main.fragment_new_password.view.*

class NewPasswordFragment : Fragment() {
    var phone: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_password, container, false)
        phone = arguments?.getString("phoneNo")

        view.update.setOnClickListener {
            if (ConnectionManager().isNetworkAvailable(context)) {
                val pass = view.etPass.text.toString().trim()
                val cnfrmPass = view.etCnfrmPass.text.toString().trim()
                if (Validations.validatePasswordLength(pass)) {
                    view.Pass.error = null
                    view.Pass.isErrorEnabled = false
                    if (Validations.matchPassword(pass, cnfrmPass)) {
                        view.CnfrmPass.error = null
                        view.CnfrmPass.isErrorEnabled = false
                        val reference: DatabaseReference =
                            FirebaseDatabase.getInstance().getReference("Users")
                        reference.child("$phone").child("password").setValue(pass)
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container, PasswordUpdatedFragment())?.commit()
                    } else {
                        view.CnfrmPass.error = "Password do not matches!!"
                    }
                } else {
                    view.Pass.error = "Password is too short!!"
                }
            } else {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Please connect internet to proceed further!!")
                    .setCancelable(false).setPositiveButton("Connect") { _, _ ->
                        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                    }.setNegativeButton("cancel") { _, _ ->
                        fragmentManager?.beginTransaction()
                            ?.replace(R.id.container, WelcomeScreenFragment())?.commit()
                        activity?.finish()
                    }
                builder.create().show()
            }
        }
        view.NewPW_back_button.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, WelcomeScreenFragment())
                ?.commit()
        }
        return view
    }
}
