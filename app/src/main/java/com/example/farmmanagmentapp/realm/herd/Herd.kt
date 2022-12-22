package com.example.farmmanagmentapp.realm.herd

import com.example.farmmanagmentapp.realm.animal.AnimalRealm
import io.realm.RealmList


data class Herd(
    val id: String,
    val name: String,
    val animals: RealmList<AnimalRealm>?
)