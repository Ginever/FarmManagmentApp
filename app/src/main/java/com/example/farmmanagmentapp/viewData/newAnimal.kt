package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentNewAnimalBinding
import com.example.farmmanagmentapp.realm.AnimalDatabaseOperations
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


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

            var boy = true

            //Save button
            saveAnimalFAB.setOnClickListener {
                if (nameEditText.text.toString() != "") {

                    //Save animal data to database
                    animalDatabaseOperations.insertAnimal(
                        name = nameEditText.text.toString(),
                        nickName = nickNameEditText.text.toString(),
                        boy = boy,
                        dateOfBirth = dateFormatter(),
                        sterilised = sterilisedCheckBox.isChecked,
                        rating = ratingSlider.rating
                    )
                    Navigation.findNavController(it).navigate(R.id.action_global_veiwDataHome)
                } else {
                    //Error reporting
                    nameEditTextLayout.error = "Name cannot be empty"
                }
            }

            maleSelectorBtn.backgroundTintList = requireContext().getResources().getColorStateList(R.color.purple_500,null)

            //Gender selector code sets one button to faded purple and one to normal purple depending on which one the user clicks
            maleSelectorBtn.setOnClickListener {
                boy = true
                maleSelectorBtn.backgroundTintList = requireContext().getResources().getColorStateList(R.color.purple_500,null)
                femaleSelectorBtn.backgroundTintList = requireContext().getResources().getColorStateList(R.color.purple_200,null)
            }
            femaleSelectorBtn.setOnClickListener {
                boy = false
                maleSelectorBtn.backgroundTintList = requireContext().getResources().getColorStateList(R.color.purple_200,null)
                femaleSelectorBtn.backgroundTintList = requireContext().getResources().getColorStateList(R.color.purple_500,null)
            }
        }
    }

    //Format date form dateSelector to dd/MM/yyyy
    private fun dateFormatter(): String {
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

