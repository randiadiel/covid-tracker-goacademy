package com.example.covidtrackergoacademy.main.presenter

import android.app.Activity
import com.example.covidtrackergoacademy.main.data.MainActivityData

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