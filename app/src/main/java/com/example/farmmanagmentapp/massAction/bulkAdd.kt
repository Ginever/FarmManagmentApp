package com.example.farmmanagmentapp.massAction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class bulkAdd : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_bulk_add, container, false)

        val numberPicker1 = rootView.findViewById<NumberPicker>(R.id.bulkAddNumberPicker1)
        val numberPicker2 = rootView.findViewById<NumberPicker>(R.id.bulkAddNumberPicker2)
        val firstGeneratedName = rootView.findViewById<TextView>(R.id.firstGeneratedName)
        val lastGeneratedName = rootView.findViewById<TextView>(R.id.lastGeneratedName)
        val prefix = rootView.findViewById<EditText>(R.id.prefix)
        val suffix = rootView.findViewById<EditText>(R.id.suffix)
        val fab = rootView.findViewById<FloatingActionButton>(R.id.bulkAddFab)


        //configuring number pickers
        numberPicker1.maxValue = 100
        numberPicker1.minValue = 0
        numberPicker1.value = 1

        numberPicker2.maxValue = 10
        numberPicker2.minValue = 2
        numberPicker2.value = 2

        //Change present last and first name based on numbers chosen
        numberPicker1.setOnValueChangedListener { _, _, i2 ->
            firstGeneratedName.text = prefix.text.toString()+ "-" + i2 + "-" + suffix.text.toString()
            numberPicker2.setMinValue(i2+1)
        }
        numberPicker2.setOnValueChangedListener { _, _, i2 ->
            lastGeneratedName.text = prefix.text.toString()+ "-" + i2 + "-" + suffix.text.toString()
            if (i2 == numberPicker2.maxValue) {
                numberPicker2.maxValue = numberPicker2.maxValue + 1
            }
            numberPicker1.maxValue = i2-1
        }

        prefix.addTextChangedListener {
            if (prefix.text.toString() == ""){
                firstGeneratedName.text = numberPicker1.value.toString() + "-" + suffix.text.toString()
                lastGeneratedName.text = numberPicker2.value.toString() + "-" + suffix.text.toString()
            } else {
                firstGeneratedName.text = prefix.text.toString() + "-" + numberPicker1.value + "-" + suffix.text.toString()
                lastGeneratedName.text = prefix.text.toString()+ "-" + numberPicker2.value + "-" + suffix.text.toString()
            }

        }
        suffix.addTextChangedListener {
            if (suffix.text.toString() == ""){
                firstGeneratedName.text = prefix.text.toString() + "-" + numberPicker1.value
                lastGeneratedName.text = prefix.text.toString()+ "-" + numberPicker2.value
            } else {
                firstGeneratedName.text = prefix.text.toString() + "-" + numberPicker1.value + "-" + suffix.text.toString()
                lastGeneratedName.text = prefix.text.toString()+ "-" + numberPicker2.value + "-" + suffix.text.toString()
            }

        }

        fab.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_bulkAdd_to_massActionsHome)
        }

        return rootView
    }
}