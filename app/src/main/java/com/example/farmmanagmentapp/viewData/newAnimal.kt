package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.AnimalAdapter
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentNewAnimalBinding
import com.example.farmmanagmentapp.realm.AnimalDatabaseOperations

class newAnimal : Fragment() {

    private var binding: FragmentNewAnimalBinding? = null

    val animalDatabaseOperations = AnimalDatabaseOperations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // create the binding for this fragment
        binding = FragmentNewAnimalBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            enterBtn.setOnClickListener {
                animalDatabaseOperations.insertAnimal(nameEditText.text.toString(),boySwitch.isChecked)
                Toast.makeText(activity, nameEditText.text.toString() + " , " + boySwitch.isChecked.toString(),Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).navigate(R.id.action_global_veiwDataHome)
            }
        }
    }
}