package com.example.farmmanagmentapp.realm.animal

import com.example.farmmanagmentapp.realm.medicine.MedicineRealm
import com.example.farmmanagmentapp.realm.species.Species
import com.example.farmmanagmentapp.realm.species.SpeciesRealm
import io.realm.RealmList


data class Animal(
    val id: String,
    val name: String,
    val nickName: String,
    val animalType: SpeciesRealm?, //Store constant animal data in a separate realm
    val dateOfBirth: String,
    val boy: Boolean,
    val sterilised: Boolean,
    val rating: Float,

    //Medicine System
    val typeMedicineAdministered: RealmList<MedicineRealm>?,
    val dateMedicineAdministered: RealmList<String>?,

    //Mating System
    val offspring: RealmList<AnimalRealm>?,
    val animalMatedWith: RealmList<AnimalRealm>?,
    val dateMatedWith: RealmList<String>?
)