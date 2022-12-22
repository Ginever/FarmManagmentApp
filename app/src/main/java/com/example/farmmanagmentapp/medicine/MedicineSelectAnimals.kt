package com.example.farmmanagmentapp.medicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmmanagmentapp.adapters.MultiAnimalsAdapter
import com.example.farmmanagmentapp.databinding.FragmentMedicineSelectAnimalsBinding
import com.example.farmmanagmentapp.realm.animal.AnimalDatabaseOperations
import kotlinx.coroutines.runBlocking

class MedicineSelectAnimals : Fragment() {

    private var animalDatabaseOperations = AnimalDatabaseOperations()
    var binding: FragmentMedicineSelectAnimalsBinding? = null
    var animalAdapter = MultiAnimalsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate binding
        binding = FragmentMedicineSelectAnimalsBinding.inflate(layoutInflater,container,false)
        runBlocking {
            animalAdapter.addItems(animalDatabaseOperations.retrieveAnimals())
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            AnimalsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            AnimalsRecyclerView.adapter = animalAdapter

            confirmSelectionFAB.setOnClickListener {
                val selectedAnimals = animalAdapter.getSelectedItems()
                if (selectedAnimals.isEmpty()){
                    Toast.makeText(activity,"Please select at least one animal", Toast.LENGTH_LONG).show()
                } else {
                    findNavController(it).navigate(MedicineSelectAnimalsDirections.actionMedicineSelectAnimalsToMedicineSelectMedicine(selectedAnimals.toTypedArray()))
                }
            }
        }

    }
}