package com.example.farmmanagmentapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.ItemAnimalBinding
import com.example.farmmanagmentapp.realm.animal.Animal
import com.example.farmmanagmentapp.viewData.ViewDataHomeDirections

class MultiAnimalsAdapter: RecyclerView.Adapter<MultiAnimalsAdapter.AnimalViewHolder>() {
    private val animals = mutableListOf<Animal>()
    private lateinit var directions: ViewDataHomeDirections
    var selectedAnimals: MutableList<String> = mutableListOf()

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

    //Add items to the dataset and update the recyclerView
    fun addItems(animalsToAdopt: List<Animal>) {
        animals.clear()
        animals.addAll(animalsToAdopt)
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = animals.size

    fun getSelectedItems(): List<String>{
        return selectedAnimals
    }

    inner class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root){

        //Set property of the animal item to contain the dataset information
        fun bind(animal: Animal){
            with(binding){
                name.text = animal.name
                if(animal.boy){
                    if (animal.sterilised){
                        genderImageView.setBackgroundResource(R.drawable.ic_male_castrated_icon)
                    } else {
                        genderImageView.setBackgroundResource(R.drawable.ic_male_icon)
                    }
                } else {
                    if (animal.sterilised){
                        genderImageView.setBackgroundResource(R.drawable.ic_female_castrated_icon)
                    } else {
                        genderImageView.setBackgroundResource(R.drawable.ic_female_icon)
                    }
                }
                dateOfBirthTextView.text = animal.dateOfBirth.substring(3,6)+animal.dateOfBirth.substring(8)

                mainBtn.setOnClickListener{
                    if (animal.id in selectedAnimals) {
                        mainBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        selectedAnimals.remove(animal.id)
                    } else {
                        selectedAnimals.add(animal.id)
                        mainBtn.setBackgroundColor(Color.parseColor("#FF0000"))
                    }
                }
            }
        }
    }
}