package com.example.farmmanagmentapp.realm.animal

import com.example.farmmanagmentapp.realm.medicine.MedicineRealm
import com.example.farmmanagmentapp.realm.species.Species
import com.example.farmmanagmentapp.realm.species.SpeciesRealm
import io.realm.RealmList
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import io.realm.RealmObject
import java.text.SimpleDateFormat
import java.util.*

open class AnimalRealm(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),

    @Required
    var name: String = "",
    var nickName: String = "",


    var animalType: SpeciesRealm? = null,
    var dateOfBirth: String = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(Calendar.getInstance().getTime()),
    var boy: Boolean = true,
    var sterilised: Boolean = false,
    var rating: Float = 0F,
    var typeMedicineAdministered: RealmList<MedicineRealm>? = null,
    var dateMedicineAdministered: RealmList<String>? = null,
    var offspring: RealmList<AnimalRealm>? = null,
    var animalMatedWith: RealmList<AnimalRealm>? = null,
    var dateMatedWith: RealmList<String>? = null


    ): RealmObject()
