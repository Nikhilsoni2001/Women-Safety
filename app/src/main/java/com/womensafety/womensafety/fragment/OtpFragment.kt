package com.womensafety.womensafety.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chaos.view.PinView
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.womensafety.womensafety.R
import com.womensafety.womensafety.activity.HomeActivity
import com.womensafety.womensafety.fragment.forget.NewPasswordFragment
import com.womensafety.womensafety.fragment.signup.CreateAccountFragment
import com.womensafety.womensafety.util.UserData
import kotlinx.android.synthetic.main.fragment_otp.view.*
import java.util.concurrent.TimeUnit

class OtpFragment : Fragment() {

    lateinit var codeBySystem: String

    private var fullName: String? = null
    private var userName: String? = null
    private var email: String? = null
    private var password: String? = null
    private var gender: String? = null
    private var dob: String? = null
    private var phone: String? = null
    private var whatToDo: String? = null

    val pinView = activity?.findViewById<PinView>(R.id.pin_view)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_otp, container, false)

        val data = arguments
        fullName = data?.getString("fullName")
        userName = data?.getString("userName")
        email = data?.getString("email")
        password = data?.getString("password")
        gender = data?.getString("gender")
        dob = data?.getString("dob")
        phone = data?.getString("phone")
        whatToDo = data?.getString("whatToDo")

        if (phone != null) {
            sendOtpToUser("$phone")
        }


        view.btnVerifyCode.setOnClickListener {
            callNextScreenFromOtp()
        }
        view.otp_exit.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, CreateAccountFragment())
                ?.commit()
        }
        return view
    }


    private fun sendOtpToUser(phone: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phone, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            TaskExecutors.MAIN_THREAD, // Activity (for callback binding)
            callbacks
        ) // OnVerificationStateChangedCallbacks
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code: String? = credential.smsCode
            if (code != null) {
                pinView?.setText("$code")
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
            codeBySystem = verificationId
        }
    }


    fun verifyCode(code: String) {
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(codeBySystem, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (whatToDo.equals("updateData")) {
                        updateOldUserData()
                    } else {
                        storeNewUsersData()
                        activity?.let {
                            val intent = Intent(it, HomeActivity::class.java)
                            startActivity(intent)
                        }

                    }
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            context,
                            "Verification not Completed!!, Please Try Again",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
    }

    private fun updateOldUserData() {
        val frag = NewPasswordFragment()
        val data = Bundle()
        data.putString("phoneNo", phone)
        frag.arguments = data
        fragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.commit()
    }

    private fun storeNewUsersData() {
        val myRef = FirebaseDatabase.getInstance().getReference("Users")
        val addNewUser = UserData(fullName, userName, email, phone, password, dob, gender)
        myRef.child("$phone").setValue(addNewUser)
    }

    private fun callNextScreenFromOtp() {
        val code: String = pinView?.text.toString()
        if (code.isEmpty()) {
            verifyCode(code)
        }
    }
}
