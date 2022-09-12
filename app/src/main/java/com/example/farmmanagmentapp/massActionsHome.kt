package com.example.farmmanagmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.navigation.Navigation

class massActionsHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_mass_actions_home, container, false)

        val bulkAddBtn = rootView.findViewById<Button>(R.id.bulkAddBtn)
        val exportBtn = rootView.findViewById<Button>(R.id.exportDataBtn)
        val importBtn = rootView.findViewById<Button>(R.id.importDataBtn)
        val bulkRemoveBtn = rootView.findViewById<Button>(R.id.bulkRemoveBtn)

        //navigate to Bulk add
        bulkAddBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_massActionsHome_to_bulkAdd)
        }

        //Navigate to Bulk remove
        bulkRemoveBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_massActionsHome_to_bulkRemove)
        }

        //Navigate to export data
        exportBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_massActionsHome_to_exportData)
        }

        //Navigate to Import Data
        importBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_massActionsHome_to_importData)
        }

        return rootView
    }
}