package com.example.farmmanagmentapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.farmmanagmentapp.databinding.ItemAnimalBinding
import com.example.farmmanagmentapp.realm.Animal
import kotlinx.coroutines.NonDisposableHandle.parent
import javax.inject.Inject

class AnimalAdapter(private val animals: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    //private val animals = mutableListOf<Animal>()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AnimalViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return AnimalViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        viewHolder.bind(animal)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = animals.size

    fun listItems(): List<Animal>{
        return animals
    }

    inner class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(animal: Animal){
            with(binding){
                name.text = animal.name
                if(animal.boy){
                    gender.text = "Male"
                } else {
                    gender.text = "Female"
                }

                mainBtn.setOnClickListener{
                    Log.d("It works", "It works!!!!!")
                }
            }
        }
    }

}