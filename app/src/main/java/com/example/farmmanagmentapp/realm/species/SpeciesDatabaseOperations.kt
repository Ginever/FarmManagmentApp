package com.example.farmmanagmentapp.realm.species

import androidx.lifecycle.ViewModel
import com.example.farmmanagmentapp.realm.animal.Animal
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import org.bson.types.ObjectId
import java.util.*

class SpeciesDatabaseOperations :ViewModel(){

    suspend fun insertSpecies(
        id: String = ObjectId().toHexString(),
        name: String,
        gestationPeriod: Int,
        menstrualLength: Int
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val animal = SpeciesRealm(name = name, id = id, gestationPeriod = gestationPeriod, menstrualLength = menstrualLength)
            // 4.
            realmTransaction.insert(animal)
        }

    }
     suspend fun retrieveSpecies(): List<Species> {
        // 1.
        val realm = Realm.getDefaultInstance()
        val animalsToAdopt = mutableListOf<Species>()

         realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
             animalsToAdopt.addAll(realmTransaction
                 // 3.
                 .where(SpeciesRealm::class.java)
                 // 4.
                 .findAll()
                 // 5.
                 .map {
                     mapSpecies(it)
                 }
             )
         }

        return animalsToAdopt
    }

    suspend fun retriveFilteredSpeciesByName(name: String): List<Species>{
        val realm = Realm.getDefaultInstance()
        val filteredAnimals = mutableListOf<Species>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredAnimals.addAll(it
                .where(SpeciesRealm::class.java)
                .contains("name", name)
                .findAll()
                .map{
                    mapSpecies(it)
                }
            )
        }

        return filteredAnimals
    }

    suspend fun deleteSpecies(animalId: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val animalToRemove = realmTransaction
                .where(SpeciesRealm::class.java)
                .equalTo("id", animalId)
                .findFirst()
            animalToRemove?.deleteFromRealm()
        }
    }

    suspend fun retrieveFilteredSpeciesById(id: String): Species {
        val realm = Realm.getDefaultInstance()
        val filteredAnimals = mutableListOf<Species>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredAnimals.addAll(it
                .where(SpeciesRealm::class.java)
                .equalTo("id",id)
                .findAll()
                .map{
                    mapSpecies(it)
                }
            )
        }
        return filteredAnimals[0]
    }

    private fun mapSpecies(species: SpeciesRealm): Species {
        return Species(
            id = species.id,
            name = species.name,
            gestationPeriod = species.gestationPeriod,
            menstrualLength = species.menstrualLength
        )
    }
}