package com.example.farmmanagmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import java.util.concurrent.TimeUnit


class ExportData : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_export_data, container, false)

        //getting buttons
        val JSONDownloadBtn = rootView.findViewById<Button>(R.id.JSONUploadBtn)
        val CSVDownloadBtn = rootView.findViewById<Button>(R.id.CSVUploadBtn)
        val XMLDownloadBtn = rootView.findViewById<Button>(R.id.XMLUploadBtn)


        //setting on click listener
        JSONDownloadBtn.setOnClickListener {
            Toast.makeText(activity,"Downloading data as .JSON",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_exportData_to_massActionsHome)
        }
        CSVDownloadBtn.setOnClickListener {
            Toast.makeText(activity,"Downloading data as .CSV",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_exportData_to_massActionsHome)
        }
        XMLDownloadBtn.setOnClickListener {
            Toast.makeText(activity,"Downloading data as .XML",Toast.LENGTH_LONG).show()
            TimeUnit.SECONDS.sleep(3L)
            Navigation.findNavController(it).navigate(R.id.action_exportData_to_massActionsHome)
        }


        return rootView
    }
}

