package com.example.covidtrackergoacademy.main

import android.app.Activity
import com.example.covidtrackergoacademy.MainActivityData

interface MainContract {
    interface View {
        fun updateData(data : MainActivityData)
        fun showError(error: String)
    }
    interface Presenter {
        fun getData(context: Activity)
        fun invisibleLoader(context: Activity)
        fun aboutDialogCall(context: Activity)
    }
}