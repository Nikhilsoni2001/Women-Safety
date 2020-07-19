package com.womensafety.womensafety.fragment.forget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.womensafety.womensafety.R
import kotlinx.android.synthetic.main.fragment_make_selection.view.*

class MakeSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_make_selection, container, false)

        view.btnEmailSelection.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, ForgetEmailFragment())?.commit()
        }

        view.btnMobileSelection.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.container, ForgetMobileFragment())
                ?.commit()
        }

        return view
    }

}