package com.example.farmmanagmentapp.realm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmmanagmentapp.MainActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimalDatabaseOperations :ViewModel(){

    fun insertAnimal(
        name: String,
        boy: Boolean
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()

        // 2.
        viewModelScope.launch {
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                // 3.
                val pet = AnimalRealm(name = name, boy = boy)
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
    private fun mapAnimal(pet: AnimalRealm): Animal {
        return Animal(
            name = pet.name,
            boy = pet.boy,
            id = pet.id
        )
    }
}