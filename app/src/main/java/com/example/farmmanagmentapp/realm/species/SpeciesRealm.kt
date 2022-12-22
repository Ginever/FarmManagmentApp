package com.example.farmmanagmentapp.realm.species

import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import io.realm.RealmObject
import io.realm.annotations.LinkingObjects
import java.text.SimpleDateFormat
import java.util.*

open class SpeciesRealm(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),

    @Required
    var name: String = "",
    var gestationPeriod: Int = 200,
    var menstrualLength: Int = 30
    ): RealmObject()
