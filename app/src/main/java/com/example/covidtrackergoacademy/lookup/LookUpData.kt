package com.example.covidtrackergoacademy.lookup

data class LookUpData(
    val provinceName: String,
    val numberOfPositiveCases: Int,
    val numberOfRecoveredCases: Int = 0,
    val numberOfDeathCases: Int = 0
)