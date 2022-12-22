package com.example.farmmanagmentapp.medicine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmmanagmentapp.adapters.MedicineAdapter
import com.example.farmmanagmentapp.databinding.FragmentMedicineSelectMedicineBinding
import com.example.farmmanagmentapp.databinding.ItemMedicineBinding
import com.example.farmmanagmentapp.realm.medicine.Medicine
import com.example.farmmanagmentapp.realm.medicine.MedicineDatabaseOperations
import com.example.farmmanagmentapp.realm.medicine.MedicineRealm
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class medicineSelectMedicine : Fragment() {

    private var binding: FragmentMedicineSelectMedicineBinding? = null
    private var medicineDatabaseOperations = MedicineDatabaseOperations()
    private var medicineAdapter = Adapter()
    private lateinit var args: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicineSelectMedicineBinding.inflate(layoutInflater,container, false)

        runBlocking {
            medicineAdapter.addItems(medicineDatabaseOperations.retrieveMedicines())
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null){
            Log.e("medicineSelectMedicine", "medicineSelectMedicine Fragment did not receive traveler information")
        } else {
            args = medicineSelectMedicineArgs.fromBundle(bundle).selectedAnimals

            medicineAdapter.selectAnimals = args

            binding?.apply {
                lifecycleScope.launch {
                    val medicine = medicineDatabaseOperations.retrieveMedicines()
                    addMedicineBtn.setOnClickListener {
                        findNavController(it).navigate(medicineSelectMedicineDirections.actionMedicineSelectMedicineToAddMedicine(args))
                    }
                }
                medicineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                medicineRecyclerView.adapter = medicineAdapter
            }
        }
    }
}

//Overwriting the navigation destination
private class Adapter:MedicineAdapter() {

    var selectAnimals: Array<String> = arrayOf()

    override fun navigate(binding: ItemMedicineBinding, medicine: Medicine) {
        binding.editMedicineBtn.setOnClickListener {
            findNavController(it).navigate(medicineSelectMedicineDirections.actionMedicineSelectMedicineToEditMedicine(medicineId = medicine.id, selectedAnimals = selectAnimals))
        }
        binding.mainBtn.setOnClickListener {
            runBlocking {
                for (x in selectAnimals){
                    val animal = animalDatabaseOperations.retrieveFilteredAnimalsById(x)
                }
            }

            //findNavController(it).navigate(medicineSelectMedicineDirections.actionMedicineSelectMedicineToMedicineDosage(selectedAnimals = selectAnimals, selectedMedicine = id))
        }
    }
}