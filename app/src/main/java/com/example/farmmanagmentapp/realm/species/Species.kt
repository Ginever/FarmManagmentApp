package com.example.farmmanagmentapp.realm.species

import com.example.farmmanagmentapp.realm.medicine.Medicine


data class Species(
    val id: String,
    val name: String,
    val gestationPeriod: Int,
    val menstrualLength: Int
)