package com.example.farmmanagmentapp.realm.medicine


data class Medicine(
    val id: String,
    val name: String,
    val brand: String,
    val meatWithholdingPeriod: Int,
    val milkWithholdingPeriod: Int
    )