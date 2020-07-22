package com.womensafety.womensafety.fragment.forget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.OtpFragment
import kotlinx.android.synthetic.main.fragment_forget_mobile.view.*

class ForgetMobileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_forget_mobile, container, false)

        view.btnVerifyCode.setOnClickListener {
            val codePicker = view.countryCodeHolder.fullNumber
            val mobile = view.etMobileNumberForget.text.toString().trim()
            val phone = "+$codePicker$mobile"

            val checkUser: Query =
                FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo")
                    .equalTo(phone)
            checkUser.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        view.MOBILEForget.error = null
                        view.MOBILEForget.isErrorEnabled = false

                        val frag = OtpFragment()
                        val bundle = Bundle()
                        bundle.putString("phone", phone)
                        bundle.putString("whatToDo", "updateData")
                        frag.arguments = bundle

                        fragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.commit()
                    } else {
                        view.MOBILEForget.error = "No such user exists!!"
                        view.MOBILEForget.requestFocus()
                    }
                }
            })
        }
        return view

    }
}

