package com.example.covidtrackergoacademy.hotline.presenter

import android.app.Activity
import com.example.covidtrackergoacademy.hotline.data.HotlineData

interface HotlineContract {
    interface View{
        fun updateData(data : MutableList<HotlineData>)
        fun showError(error: String)
        fun closeBottomSheet()
        fun changeBackgroundToRectangle(set: Boolean)
    }
    interface Presenter{
        fun getData()
        fun bottomSheetOpener(context: Activity)
    }
}