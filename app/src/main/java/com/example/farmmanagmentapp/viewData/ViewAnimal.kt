package com.example.farmmanagmentapp.viewData

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentViewAnimalBinding
import com.example.farmmanagmentapp.realm.animal.AnimalDatabaseOperations
import kotlinx.coroutines.launch


class ViewAnimal : Fragment() {

    private var binding: FragmentViewAnimalBinding? = null

    val animalDatabaseOperations = AnimalDatabaseOperations()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewAnimalBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null){
            Log.e("ViewAnimal", "ViewAnimal fragment did not receive traveler information")
        } else {
            val args = ViewAnimalArgs.fromBundle(bundle)
            lifecycleScope.launch{
                val animal = animalDatabaseOperations.retrieveFilteredAnimalsById(args.animalId)
                binding?.apply {
                    //Setting text fields to animal values
                    nameText.text = animal.name

                    if (animal.nickName == ""){
                        nickNameText.text = "N/A"
                    } else {
                        nickNameText.text = animal.nickName
                    }
                    if (animal.boy){
                        genderText.text = "Male"
                    } else {
                        genderText.text = "Female"
                    }
                    dateOfBirthText.text = animal.dateOfBirth
                    sterilisedText.text = animal.sterilised.toString()
                    ratingBar.rating = animal.rating


                    floatingActionButton2.setOnClickListener{
                        Navigation.findNavController(it).navigate(ViewAnimalDirections.actionViewAnimalToEditAnimal(animal.id))
                    }

                    deleteBtn.setOnClickListener {
                        //Set up pop up confirmation menu
                        val alertDialogBuilder = AlertDialog.Builder(activity)
                        alertDialogBuilder.setMessage("Are you sure you want to delete this animal?")
                        alertDialogBuilder.setPositiveButton("yes") { _, _ ->
                            //Delete animal and navigate to viewDataHome fragment
                            lifecycleScope.launch() {
                                animalDatabaseOperations.deleteAnimal(animal.id)
                                Navigation.findNavController(it).navigate(R.id.action_global_veiwDataHome)
                            }
                        }
                        alertDialogBuilder.setNegativeButton("No") { _, _ ->
                                //Do nothing: This has to be here for syntax
                            }

                        //create alert and display it
                        alertDialogBuilder.create().show()

                    }
                }
            }
        }
    }
}