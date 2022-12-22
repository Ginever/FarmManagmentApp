package com.example.farmmanagmentapp.medicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentMedicineDosageBinding

class medicineDosage : Fragment() {

    var binding: FragmentMedicineDosageBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicineDosageBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
}