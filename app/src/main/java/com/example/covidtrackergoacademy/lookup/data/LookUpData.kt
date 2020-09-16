package com.example.covidtrackergoacademy.lookup.data

data class LookUpData(
    val provinceName: String,
    val numberOfPositiveCase: Int,
    val numberOfRecoveredCase: Int,
    val numberOfDeathCase: Int
)