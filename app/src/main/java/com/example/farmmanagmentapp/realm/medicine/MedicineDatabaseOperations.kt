package com.example.farmmanagmentapp.realm.medicine

import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import org.bson.types.ObjectId

class MedicineDatabaseOperations :ViewModel(){

    suspend fun insertMedicine(
        id: String = ObjectId().toHexString(),
        name: String,
        brand: String = "",
        meatWithholdingPeriod: Int = 0,
        milkWithholdingPeriod: Int = 0
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val medicine = MedicineRealm(id =id, name = name, brand = brand, meatWithholdingPeriod = meatWithholdingPeriod, milkWithholdingPeriod = milkWithholdingPeriod)
            // 4.
            realmTransaction.insert(medicine)
        }

    }

    suspend fun updateMedicine(
        id: String = ObjectId().toHexString(),
        name: String,
        brand: String = "",
        meatWithholdingPeriod: Int = 0,
        milkWithholdingPeriod: Int = 0
    ) {
        // 1.
        val realm = Realm.getDefaultInstance()


        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            // 3.
            val medicine = MedicineRealm(id =id, name = name, brand = brand, meatWithholdingPeriod = meatWithholdingPeriod, milkWithholdingPeriod = milkWithholdingPeriod)
            // 4.
            realmTransaction.insertOrUpdate(medicine)
        }

    }

     suspend fun retrieveMedicines(): List<Medicine> {
        // 1.
        val realm = Realm.getDefaultInstance()
        val medicines = mutableListOf<Medicine>()

         realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
             medicines.addAll(realmTransaction
                 // 3.
                 .where(MedicineRealm::class.java)
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

    suspend fun retriveFilteredMedicineById(id:String): Medicine{
        val realm = Realm.getDefaultInstance()
        var medicine = mutableListOf<Medicine>()
        realm.executeTransactionAwait(Dispatchers.IO){
            medicine.addAll(it
                .where(MedicineRealm::class.java)
                .contains("id",id)
                .findAll()
                .map{
                    mapMedicine(it)
                }
            )
        }
        return medicine[0]
    }

    suspend fun retriveFilteredMedicinesByName(name: String): List<Medicine>{
        val realm = Realm.getDefaultInstance()
        val filteredMedicines = mutableListOf<Medicine>()

        realm.executeTransactionAwait(Dispatchers.IO) {
            filteredMedicines.addAll(it
                .where(MedicineRealm::class.java)
                .contains("name", name)
                .findAll()
                .map{
                    mapMedicine(it)
                }
            )
        }

        return filteredMedicines
    }

    suspend fun deleteMedicine(petId: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val medicineToRemove = realmTransaction
                .where(MedicineRealm::class.java)
                .equalTo("id", petId)
                .findFirst()
            medicineToRemove?.deleteFromRealm()
        }
    }

    private fun mapMedicine(medicine: MedicineRealm): Medicine {
        return Medicine(
            id = medicine.id,
            name = medicine.name,
            brand = medicine.brand,
            meatWithholdingPeriod = medicine.meatWithholdingPeriod,
            milkWithholdingPeriod = medicine.milkWithholdingPeriod
        )
    }
}