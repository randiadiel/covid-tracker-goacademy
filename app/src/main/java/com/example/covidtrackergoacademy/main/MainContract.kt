package com.example.covidtrackergoacademy.main

import com.example.covidtrackergoacademy.MainActivityData

interface MainContract {
    interface View {
        fun updateData(data : MainActivityData)
        fun showError(error: String)
    }
    interface Presenter {
        fun getData()
    }
}