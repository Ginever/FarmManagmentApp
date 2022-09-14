package com.example.farmmanagmentapp.massAction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R
import java.util.concurrent.TimeUnit


class importData : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_import_data, container, false)

        //getting buttons
        val JSONUploadBtn = rootView.findViewById<Button>(R.id.JSONUploadBtn)
        val CSVUploadBtn = rootView.findViewById<Button>(R.id.CSVUploadBtn)
        val XMLUploadBtn = rootView.findViewById<Button>(R.id.XMLUploadBtn)


        //setting on click listener
        JSONUploadBtn.setOnClickListener {
            Toast.makeText(activity,"Uploading data as .JSON",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_importData_to_massActionsHome)
        }
        CSVUploadBtn.setOnClickListener {
            Toast.makeText(activity,"Uploading data as .CSV",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_importData_to_massActionsHome)
        }
        XMLUploadBtn.setOnClickListener {
            Toast.makeText(activity,"Uploading data as .XML",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_importData_to_massActionsHome)
        }


        return rootView
    }
}

