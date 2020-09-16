package com.example.covidtrackergoacademy.lookup.mvp

import com.example.covidtrackergoacademy.lookup.LookUpData

interface LookUpContract {
    interface View {
        fun updateData(data : MutableList<LookUpData>)
        fun showError(error : String)
    }

    interface Presenter {
        fun getData()
    }
}