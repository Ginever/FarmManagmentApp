package com.example.farmmanagmentapp.realm.medicine

import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import io.realm.RealmObject
import java.text.SimpleDateFormat
import java.util.*

open class MedicineRealm(
    @PrimaryKey
    var id: String = ObjectId().toHexString(),

    @Required
    var name: String = "",

    var brand: String = "",
    var meatWithholdingPeriod: Int = 0,
    var milkWithholdingPeriod: Int = 0
    ): RealmObject()
