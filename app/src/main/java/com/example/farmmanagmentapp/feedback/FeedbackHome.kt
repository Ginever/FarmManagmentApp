package com.example.farmmanagmentapp.feedback

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R

class feedbackHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_feedback_home, container, false)

        val submitBtn = rootView.findViewById<Button>(R.id.submitBtn)
        val editText = rootView.findViewById<EditText>(R.id.editTextTextMultiLine3)

        submitBtn.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_feedbackHome_to_feedbackThankYou)
        }

        editText.setOnEditorActionListener { _, _, _ -> Log.d(TAG, "onCreateView: "); true}

        return rootView
    }
}