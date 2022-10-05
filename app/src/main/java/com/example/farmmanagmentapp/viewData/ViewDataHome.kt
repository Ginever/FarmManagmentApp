package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmmanagmentapp.AnimalAdapter
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.FragmentViewDataHomeBinding
import com.example.farmmanagmentapp.realm.AnimalDatabaseOperations
import io.realm.Realm
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ViewDataHome: Fragment() {

    //configure Variables
    val animalDatabaseOperations = AnimalDatabaseOperations()
    var realm: Realm? = null
    private var binding: FragmentViewDataHomeBinding? = null
    var animalAdapter = AnimalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewDataHomeBinding.inflate(layoutInflater,container,false)
        /*lifecycleScope.launch{
        for (x in 1..10000){
            animalDatabaseOperations.insertAnimal(name = x.toString(), nickName = "Nickname $x",boy = (x%2 == 1), sterilised = (x%4 < 2), rating = x%6F)
            if (x%100 == 0){
                Log.d("Amount", x.toString())
            }
        }
        }*/

        //Set up the animal adapter with the animal data
        runBlocking {
            animalAdapter.addItems(animalDatabaseOperations.retrieveAnimals())
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {

            //making sure the search icon remains hidden if there is text in the search bar
            if (searchBar.text.toString() != ""){
                searchIcon.visibility = View.GONE
            }

            //set up FAB
            addAnimal.setOnClickListener{
                findNavController().navigate(R.id.action_viewDataHome_to_newAnimal)
            }

            //Hiding the search icon when search bar clicked
            searchBar.setOnFocusChangeListener { _, b ->
                if (b){
                    searchIcon.visibility = View.GONE
                } else {
                    searchIcon.visibility = View.VISIBLE
                }
            }

            //When the search bar text is changed update the recyclerView
            searchBar.addTextChangedListener {
                lifecycleScope.launch{
                    animalAdapter.addItems(animalDatabaseOperations.retriveFilteredAnimalsByName(searchBar.text.toString()))
                }
            }


            //apply layout and adapter to recycleView
            herdRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            herdRecyclerView.adapter = animalAdapter
        }
    }
}