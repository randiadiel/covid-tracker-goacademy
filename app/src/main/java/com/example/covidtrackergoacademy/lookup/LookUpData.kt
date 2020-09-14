package com.example.covidtrackergoacademy.lookup

data class LookUpData(
    val provinceName: String,
    val numberOfPositiveCase: Int,
    val numberOfRecoveredCase: Int,
    val numberOfDeathCase: Int
)