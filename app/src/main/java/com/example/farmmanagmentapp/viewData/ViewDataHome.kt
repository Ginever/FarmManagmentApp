package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmmanagmentapp.AnimalAdapter
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentViewDataHomeBinding
import com.example.farmmanagmentapp.realm.AnimalDatabaseOperations
import io.realm.Realm
import kotlinx.coroutines.runBlocking


class ViewDataHome: Fragment() {

    //configure Variables
    var realm: Realm? = null
    private var binding: FragmentViewDataHomeBinding? = null
    val animalDatabaseOperations = AnimalDatabaseOperations()
    lateinit var animalAdapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewDataHomeBinding.inflate(layoutInflater,container,false)

        //Set up the animal adapter with the animal data
        runBlocking {
            animalAdapter = AnimalAdapter(animalDatabaseOperations.retrieveAnimals())
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {

            //set up FAB
            addAnimal.setOnClickListener{
                findNavController().navigate(R.id.action_viewDataHome_to_newAnimal)
            }

            //apply layout and adapter to recycleView
            herdRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            herdRecyclerView.adapter = animalAdapter
        }
    }
}