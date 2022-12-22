package com.example.farmmanagmentapp.viewData

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.adapters.singleAnimalAdapter
import com.example.farmmanagmentapp.databinding.FragmentViewDataHomeBinding
import com.example.farmmanagmentapp.databinding.ItemAnimalBinding
import com.example.farmmanagmentapp.realm.animal.AnimalDatabaseOperations
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ViewDataHome: Fragment() {

    //configure Variables
    private val animalDatabaseOperations = AnimalDatabaseOperations()
    private var binding: FragmentViewDataHomeBinding? = null
    private var animalAdapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewDataHomeBinding.inflate(layoutInflater,container,false)
        /*lifecycleScope.launch{
        for (x in 1..100){
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
            searchBar.inputType = 1
            //making sure the search icon remains hidden if there is text in the search bar
            if (searchBar.text.toString() != ""){
                searchIcon.visibility = View.GONE
            }

            //Hiding the search icon when search bar clicked
            searchBar.setOnFocusChangeListener { _, b ->
                if (b){
                    searchIcon.visibility = View.GONE
                } else if (searchBar.text.toString() == "") {
                    searchIcon.visibility = View.VISIBLE

                }
            }


            //Hiding the keyboard if the enter key is pressed
            /*searchBar.setOnKeyListener { view, i, keyEvent ->
                if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                        val imm = getSystemService(activity) as InputMethodManager?
                        imm!!.hideSoftInputFromWindow(searchBar.getWindowToken(), 0)
                    return@setOnKeyListener true
                    }
                return@setOnKeyListener false
            }*/

            //When the search bar text is changed update the recyclerView
            searchBar.addTextChangedListener {
                lifecycleScope.launch{
                    animalAdapter.addItems(animalDatabaseOperations.retrieveFilteredAnimalsByName(searchBar.text.toString()))
                }
            }
            addAnimal.setOnClickListener{
                findNavController().navigate(R.id.action_viewDataHome_to_newAnimal)
            }

            //apply layout and adapter to recycleView
            herdRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            herdRecyclerView.adapter = animalAdapter
        }
    }
}


//Override the navigation from the Animal RecyclerView adapter so that it navigates to the correct location
private class Adapter : singleAnimalAdapter(){
    override fun navigate(binding: ItemAnimalBinding, id: String){
        binding.mainBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(ViewDataHomeDirections.actionViewDataHomeToViewAnimal(id))
        }
    }

}