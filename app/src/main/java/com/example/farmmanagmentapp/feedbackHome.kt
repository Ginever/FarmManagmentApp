package com.example.farmmanagmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class feedbackHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_feedback_home, container, false)

        val submitBtn = rootView.findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_feedbackHome_to_feedbackThankYou)
        }

        return rootView
    }
}