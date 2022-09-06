package com.example.farmmanagmentapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout

class ViewDataHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_view_data_home, container, false)

        //Get views that are going to have buttons dynamically created in them
        val herdView = rootView.findViewById<LinearLayout>(R.id.herdScroll)
        val animalView = rootView.findViewById<LinearLayout>(R.id.animalScroll)

        for (i in 1..10){
            val horLayout = LinearLayout(activity)
            horLayout.gravity = 3
            for (t in 1..3) {
                val btn = Button(activity)
                btn.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                btn.text = i.toString() + ',' + t.toString()
                horLayout.addView(btn)
                btn.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            animalView.addView(horLayout)
        }

        return rootView
    }
}