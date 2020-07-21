package com.womensafety.womensafety.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import com.google.android.material.animation.AnimationUtils
import com.womensafety.womensafety.R
import com.womensafety.womensafety.fragment.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*


class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_splash_screen, container, false)
        val sideAnim: Animation =android.view.animation.AnimationUtils.loadAnimation(context,R.anim.side_anim)
        val bottomAnim: Animation =android.view.animation.AnimationUtils.loadAnimation(context,R.anim.bottom_anim)
        view?.logo?.animation=sideAnim
        view?.logo?.animation=bottomAnim
        view?.logo_name?.animation=sideAnim
        view?.logo_name?.animation=bottomAnim

        Handler(Looper.getMainLooper()).postDelayed({
            fragmentManager?.beginTransaction()?.replace(R.id.container, WelcomeScreenFragment())?.commit()
        }, 2000)
        return view

    }

}