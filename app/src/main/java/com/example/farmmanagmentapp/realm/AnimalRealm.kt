package com.example.farmmanagmentapp.realm

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
    var boy: Boolean = true,
    var dateOfBirth: String = SimpleDateFormat("dd/MM/yy",Locale.getDefault()).format(Calendar.getInstance().getTime()),
    var sterilised: Boolean = false,
    var rating: Float = 0F
    ): RealmObject()
