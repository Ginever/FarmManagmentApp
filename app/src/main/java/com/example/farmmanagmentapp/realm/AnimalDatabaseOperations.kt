package com.example.farmmanagmentapp.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bson.types.ObjectId
import java.text.SimpleDateFormat
import java.util.*

class AnimalDatabaseOperations :ViewModel(){

    fun insertAnimal(
        id: String = ObjectId().toHexString(),
        name: String,
        nickName: String = "",
        boy: Boolean,
        dateOfBirth: String = SimpleDateFormat("dd/MM/yy",Locale.getDefault()).format(Calendar.getInstance().time),
        sterilised: Boolean = false,
        rating: Float = 0F
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()

        // 2.
        viewModelScope.launch {
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                // 3.
                val pet = AnimalRealm(name = name, nickName = nickName, boy = boy, id = id, dateOfBirth = dateOfBirth, sterilised = sterilised, rating = rating)
                // 4.
                realmTransaction.insert(pet)
            }
        }

    }
     suspend fun retrieveAnimals(): List<Animal> {
        // 1.
        val realm = Realm.getDefaultInstance()
        val petsToAdopt = mutableListOf<Animal>()

         realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
             petsToAdopt.addAll(realmTransaction
                 // 3.
                 .where(AnimalRealm::class.java)
                 // 4.
                 .findAll()
                 // 5.
                 .map {
                     mapAnimal(it)
                 }
             )
         }

        return petsToAdopt
    }

    suspend fun retriveFilteredAnimalsByName(name: String): List<Animal>{
        val realm = Realm.getDefaultInstance()
        val filteredAnimals = mutableListOf<Animal>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredAnimals.addAll(it
                .where(AnimalRealm::class.java)
                .contains("name", name)
                .findAll()
                .map{
                    mapAnimal(it)
                }
            )
        }

        return filteredAnimals
    }

    suspend fun deleteAnimal(petId: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val petToRemove = realmTransaction
                .where(AnimalRealm::class.java)
                .equalTo("id", petId)
                .findFirst()
            petToRemove?.deleteFromRealm()
        }
    }

    suspend fun retriveFilteredAnimalsById(id: String): Animal{
        val realm = Realm.getDefaultInstance()
        val filteredAnimals = mutableListOf<Animal>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredAnimals.addAll(it
                .where(AnimalRealm::class.java)
                .equalTo("id",id)
                .findAll()
                .map{
                    mapAnimal(it)
                }
            )
        }
        return filteredAnimals[0]
    }

    private fun mapAnimal(pet: AnimalRealm): Animal {
        return Animal(
            name = pet.name,
            nickName = pet.nickName,
            boy = pet.boy,
            id = pet.id,
            dateOfBirth = pet.dateOfBirth,
            sterilised = pet.sterilised,
            rating = pet.rating
        )
    }
}