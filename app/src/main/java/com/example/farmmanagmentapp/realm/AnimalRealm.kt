package com.example.farmmanagmentapp.realm

import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import io.realm.RealmObject

open class AnimalRealm(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),

    @Required
    var name: String = "",
    var boy: Boolean = true

): RealmObject()
