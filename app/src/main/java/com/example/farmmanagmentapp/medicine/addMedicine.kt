package com.example.farmmanagmentapp.medicine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.example.farmmanagmentapp.databinding.FragmentAddMedicineBinding
import com.example.farmmanagmentapp.realm.medicine.MedicineDatabaseOperations
import kotlinx.coroutines.launch

class addMedicine : Fragment() {

    var binding: FragmentAddMedicineBinding? = null
    val medicineDatabaseOperations = MedicineDatabaseOperations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMedicineBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null){
            Log.e("addMedicine", "addMedicine fragment did not receive traveler information")
        } else {
            val args = addMedicineArgs.fromBundle(bundle).selectedAnimals


            binding?.apply {

                //setting number picker parameters
                milkWitholdingNumberPicker.wrapSelectorWheel = false
                milkWitholdingNumberPicker.maxValue = 1000
                milkWitholdingNumberPicker.minValue = 0

                meatWIthholdingNumberPicker.wrapSelectorWheel = false
                meatWIthholdingNumberPicker.maxValue = 1000
                meatWIthholdingNumberPicker.minValue = 0

                createMedicineBtn.setOnClickListener {
                    if (!(nameEditText.text.toString().isBlank() || brandEditText.text.toString()
                            .isBlank())
                    ) {
                        lifecycleScope.launch {
                            medicineDatabaseOperations.insertMedicine(
                                name = nameEditText.text.toString(),
                                brand = brandEditText.text.toString(),
                                meatWithholdingPeriod = meatWIthholdingNumberPicker.value,
                                milkWithholdingPeriod = milkWitholdingNumberPicker.value
                            )
                            findNavController(it).navigate(addMedicineDirections.actionAddMedicineToMedicineSelectMedicine(args))
                        }
                    } else {
                        if (nameEditText.text.toString().isBlank()) {
                            medicineNameEditText.error = "Name cannot be empty"
                        }
                        if (brandEditText.text.toString().isBlank()) {
                            brandNameEditText.error = "Brand cannot be empty"
                        }
                    }

                }
            }
        }
    }
}