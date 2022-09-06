package com.example.farmmanagmentapp

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

class homeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView =  inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        val viewDataBtn = rootView.findViewById<Button>(R.id.viewDataBtn)
        val feedbackBtn = rootView.findViewById<Button>(R.id.feedbackBtn)
        val massActionsBtn = rootView.findViewById<Button>(R.id.massActionsBtn)
        val matingBtn = rootView.findViewById<Button>(R.id.matingBtn)
        val birthBtn = rootView.findViewById<Button>(R.id.recordBirthBtn)

        //Navigation to view data
        viewDataBtn.setOnClickListener {view: View ->
            findNavController(view).navigate(R.id.action_homeFragment_to_veiwDataHome)
        }

        //Navigation to feedback
        feedbackBtn.setOnClickListener { view: View ->
            findNavController(view).navigate(R.id.action_homeFragment_to_feedbackHome)
        }

        //Navigate to Mass actions
        massActionsBtn.setOnClickListener { view: View ->
            findNavController(view).navigate(R.id.action_homeFragment_to_massActionsHome)
        }

        //Navigate to mating
        matingBtn.setOnClickListener {
            findNavController(it).navigate(R.id.action_homeFragment_to_matingHome)
        }

        //Navigate to birth
        birthBtn.setOnClickListener {
            findNavController(it).navigate(R.id.action_homeFragment_to_birthMothersInfo)
        }
        return rootView
    }
}