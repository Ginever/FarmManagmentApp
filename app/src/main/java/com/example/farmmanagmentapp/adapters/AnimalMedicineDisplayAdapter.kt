package com.example.farmmanagmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmmanagmentapp.databinding.ItemDisplayMedicineBinding
import com.example.farmmanagmentapp.databinding.ItemMedicineBinding
import com.example.farmmanagmentapp.realm.medicine.Medicine

class AnimalMedicineDisplayAdapter: RecyclerView.Adapter<AnimalMedicineDisplayAdapter.MedicineViewHolder>() {
    private val medicines = mutableListOf<Medicine>()
    lateinit var binding: ItemMedicineBinding

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MedicineViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ItemDisplayMedicineBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MedicineViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        viewHolder.bind(medicine)
    }

    //Add items to the dataset and update the recyclerView
    fun addItems(medicinesToAdd: List<Medicine>) {
        medicines.clear()
        medicines.addAll(medicinesToAdd)
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = medicines.size

    //Navigate function is intended to be overwritten as the navigation destination will be different in all usages
    open fun navigate(binding: ItemDisplayMedicineBinding, id: String){
        throw NoSuchMethodException("The destination has not been overwritten")
    }

    inner class MedicineViewHolder(private val binding: ItemDisplayMedicineBinding) : RecyclerView.ViewHolder(binding.root){

        //Set property of the animal item to contain the dataset information
        fun bind(medicine: Medicine){
            with(binding){
                name.text = medicine.name
                navigate(binding,medicine.id)
            }
        }
    }
}