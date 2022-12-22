package com.example.farmmanagmentapp.realm.herd

import androidx.lifecycle.ViewModel
import com.example.farmmanagmentapp.realm.animal.AnimalRealm
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import org.bson.types.ObjectId

class HerdDatabaseOperations :ViewModel(){

    suspend fun insertHerd(
        id: String = ObjectId().toHexString(),
        name: String,
        animals: RealmList<AnimalRealm>? = null
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val medicine = HerdRealm(id =id, name = name, animals = animals)
            // 4.
            realmTransaction.insert(medicine)
        }

    }
     suspend fun retrieveHerds(): List<Herd> {
        // 1.
        val realm = Realm.getDefaultInstance()
        val medicines = mutableListOf<Herd>()

         realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
             medicines.addAll(realmTransaction
                 // 3.
                 .where(HerdRealm::class.java)
                 // 4.
                 .findAll()
                 // 5.
                 .map {
                     mapMedicine(it)
                 }
             )
         }

        return medicines
    }

    suspend fun retriveFilteredHerdsByName(name: String): List<Herd>{
        val realm = Realm.getDefaultInstance()
        val filteredMedicines = mutableListOf<Herd>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredMedicines.addAll(it
                .where(HerdRealm::class.java)
                .contains("name", name)
                .findAll()
                .map{
                    mapMedicine(it)
                }
            )
        }

        return filteredMedicines
    }

    suspend fun deleteHerd(herdId: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val medicineToRemove = realmTransaction
                .where(HerdRealm::class.java)
                .equalTo("id", herdId)
                .findFirst()
            medicineToRemove?.deleteFromRealm()
        }
    }

    private fun mapMedicine(herd: HerdRealm): Herd {
        return Herd(
            id = herd.id,
            name = herd.name,
            animals = herd.animals
        )
    }
}