package com.example.farmmanagmentapp.realm.animal

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.farmmanagmentapp.realm.medicine.MedicineRealm
import com.example.farmmanagmentapp.realm.species.SpeciesRealm
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import org.bson.types.ObjectId
import java.text.SimpleDateFormat
import java.util.*

class AnimalDatabaseOperations :ViewModel(){

    suspend fun insertAnimal(
        id: String = ObjectId().toHexString(),
        name: String,
        nickName: String = "",
        animalType: SpeciesRealm? = null,
        boy: Boolean,
        dateOfBirth: String = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(Calendar.getInstance().time),
        sterilised: Boolean = false,
        rating: Float = 0F,
        typeMedicineAdministered: RealmList<MedicineRealm>? = null,
        dateMedicineAdministered: RealmList<String>? = null,
        offspring: RealmList<AnimalRealm>? = null,
        animalMatedWith: RealmList<AnimalRealm>? = null,
        dateMatedWith: RealmList<String>? = null
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val animal = AnimalRealm(id = id, name = name, nickName = nickName, animalType = animalType, boy = boy, dateOfBirth = dateOfBirth, sterilised = sterilised, rating = rating, typeMedicineAdministered = typeMedicineAdministered, dateMedicineAdministered = dateMedicineAdministered, offspring = offspring, animalMatedWith = animalMatedWith, dateMatedWith = dateMatedWith)
            // 4.
            realmTransaction.insert(animal)
        }

    }

    suspend fun updateAnimal(animal:Animal) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val animal = AnimalRealm(id = animal.id, name = animal.name, nickName = animal.nickName, animalType = animal.animalType, boy = animal.boy, dateOfBirth = animal.dateOfBirth, sterilised = animal.sterilised, rating = animal.rating, typeMedicineAdministered = animal.typeMedicineAdministered, dateMedicineAdministered = animal.dateMedicineAdministered, offspring = animal.offspring, animalMatedWith = animal.animalMatedWith, dateMatedWith = animal.dateMatedWith)
            // 4.
            realmTransaction.insertOrUpdate(animal)
        }

    }
     suspend fun retrieveAnimals(): List<Animal> {
        // 1.
        val realm = Realm.getDefaultInstance()
        val animals = mutableListOf<Animal>()

         realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
             animals.addAll(realmTransaction
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
         return animals
    }

    suspend fun retrieveFilteredAnimalsByName(name: String): List<Animal>{
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


    suspend fun deleteAnimal(animalId: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val animalToRemove = realmTransaction
                .where(AnimalRealm::class.java)
                .equalTo("id", animalId)
                .findFirst()
            animalToRemove?.deleteFromRealm()
        }
    }

    suspend fun retrieveFilteredAnimalsById(id: String): Animal {
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

    private fun mapAnimal(animal: AnimalRealm): Animal {
        return Animal(
            id = animal.id,
            name = animal.name,
            nickName = animal.nickName,
            animalType = animal.animalType,
            boy = animal.boy,
            dateOfBirth = animal.dateOfBirth,
            sterilised = animal.sterilised,
            rating = animal.rating,
            typeMedicineAdministered = animal.typeMedicineAdministered,
            dateMedicineAdministered = animal.dateMedicineAdministered,
            offspring = animal.offspring,
            animalMatedWith = animal.animalMatedWith,
            dateMatedWith = animal.dateMatedWith
        )
    }
}