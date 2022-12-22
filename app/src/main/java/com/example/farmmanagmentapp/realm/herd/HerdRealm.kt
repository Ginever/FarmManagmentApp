package com.example.farmmanagmentapp.realm.herd

import com.example.farmmanagmentapp.realm.animal.AnimalRealm
import io.realm.RealmList
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import io.realm.RealmObject
import java.text.SimpleDateFormat
import java.util.*

open class HerdRealm(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),

    @Required
    var name: String = "",
    var animals: RealmList<AnimalRealm>? = null
    ): RealmObject()
