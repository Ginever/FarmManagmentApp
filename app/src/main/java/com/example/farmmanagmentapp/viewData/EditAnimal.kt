package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentEditAnimalBinding
import com.example.farmmanagmentapp.realm.AnimalDatabaseOperations
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class EditAnimal : Fragment() {

    var binding: FragmentEditAnimalBinding? = null
    val animalDatabaseOperations = AnimalDatabaseOperations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditAnimalBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null){
            Log.e("EditAnimal", "EditAnimal fragment did not receive traveler information", )
        } else {
            lifecycleScope.launch {
                val animal = animalDatabaseOperations.retriveFilteredAnimalsById(ViewAnimalArgs.fromBundle(bundle).animalId)
                binding?.apply {

                    //setting simple to set values
                    nameEditText.setText(animal.name)
                    nickNameEditText.setText(animal.nickName)
                    sterilisedCheckBox.isChecked = animal.sterilised
                    var boy = animal.boy


                    //configureDatePicker
                    val date = animal.dateOfBirth
                    datePicker.updateDate(date.subSequence(6,date.length).toString().toInt(),date.subSequence(3,5).toString().toInt()-1,date.subSequence(0,2).toString().toInt())

                    ratingSlider.rating = animal.rating

                    //configureGenderPicker
                    if (boy){
                        maleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_500,null)
                        femaleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_200,null)
                    } else {
                        maleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_200,null)
                        femaleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_500,null)
                    }

                    //Gender selector code sets one button to faded purple and one to normal purple depending on which one the user clicks
                    maleSelectorBtn.setOnClickListener {
                        boy = true
                        maleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_500,null)
                        femaleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_200,null)
                    }
                    femaleSelectorBtn.setOnClickListener {
                        boy = false
                        maleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_200,null)
                        femaleSelectorBtn.backgroundTintList = requireContext().resources.getColorStateList(R.color.purple_500,null)
                    }

                    //Save data and navigate to viewDataHome when FAB clicked
                    saveAnimalFAB.setOnClickListener{
                        lifecycleScope.launch {
                            animalDatabaseOperations.deleteAnimal(animal.id)
                            animalDatabaseOperations.insertAnimal(
                                id = animal.id,
                                name = nameEditText.text.toString(),
                                nickName = nickNameEditText.text.toString(),
                                boy = boy,
                                dateOfBirth = getDate(),
                                sterilised = sterilisedCheckBox.isChecked,
                                rating = ratingSlider.rating
                            )
                            Navigation.findNavController(it).navigate(R.id.action_global_veiwDataHome)
                        }
                    }
                }
            }
        }
    }

    //Format date form dateSelector to dd/MM/yyyy
    private fun getDate(): String {
        var day = ""
        var month = ""
        var year = ""
        binding?.apply {
            day = datePicker.dayOfMonth.toString()
            month = (datePicker.month + 1).toString()
            year = datePicker.year.toString()

            if (day.toInt()<10){
                day = "0$day"
            }
            if (month.toInt()<10){
                month = "0$month"
            }

        }
        return "$day/$month/$year"
    }
}