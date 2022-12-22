package com.example.farmmanagmentapp.medicine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R import com.example.farmmanagmentapp.databinding.FragmentEditMedicineBinding
import com.example.farmmanagmentapp.realm.medicine.MedicineDatabaseOperations
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class editMedicine : Fragment() {

    var binding: FragmentEditMedicineBinding? = null
    val medicineDatabaseOperations = MedicineDatabaseOperations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMedicineBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments
        if (bundle == null){
            Log.e("editMedicine", "editMedicine Fragment did not receive traveler information")
        } else {
            val medicineId = editMedicineArgs.fromBundle(bundle).medicineId


            binding?.apply {
                lifecycleScope.launch {
                    var medicine = medicineDatabaseOperations.retriveFilteredMedicineById(medicineId)

                    editMeatWithholdingNumberPicker.maxValue = 1000
                    editMeatWithholdingNumberPicker.minValue = 0
                    editMeatWithholdingNumberPicker.wrapSelectorWheel = false

                    editMilkWitholdingNumberPicker.maxValue = 1000
                    editMilkWitholdingNumberPicker.minValue = 0
                    editMilkWitholdingNumberPicker.wrapSelectorWheel = false


                    editNameEditText.setText(medicine.name)
                    editBrandEditText.setText(medicine.brand)
                    editMeatWithholdingNumberPicker.value = medicine.meatWithholdingPeriod
                    editMilkWitholdingNumberPicker.value = medicine.milkWithholdingPeriod
                }

                saveEditedMedicineFAB.setOnClickListener {
                    lifecycleScope.launch {
                        medicineDatabaseOperations.deleteMedicine(medicineId)
                        medicineDatabaseOperations.insertMedicine(
                            id = medicineId,
                            name = editNameEditText.text.toString(),
                            brand = editBrandEditText.text.toString(),
                            milkWithholdingPeriod = editMilkWitholdingNumberPicker.value,
                            meatWithholdingPeriod = editMeatWithholdingNumberPicker.value
                        )
                        Navigation.findNavController(it).navigate(editMedicineDirections.actionEditMedicineToMedicineSelectMedicine(editMedicineArgs.fromBundle(bundle).selectedAnimals))
                    }
                }
            }
        }
    }
}
