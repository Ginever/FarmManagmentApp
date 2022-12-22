package com.example.farmmanagmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmmanagmentapp.R
import com.example.farmmanagmentapp.databinding.ItemAnimalBinding
import com.example.farmmanagmentapp.realm.animal.Animal

open class singleAnimalAdapter : RecyclerView.Adapter<singleAnimalAdapter.AnimalViewHolder>() {

    private val animals = mutableListOf<Animal>()
    lateinit var binding: ItemAnimalBinding

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

    //Navigate function is intended to be overwritten as the navigation destination will be different in all usages
    open fun navigate(binding: ItemAnimalBinding, id: String){
        throw NoSuchMethodException("The destination has not been overwritten")
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

                navigate(binding,animal.id)
            }
        }
    }

}