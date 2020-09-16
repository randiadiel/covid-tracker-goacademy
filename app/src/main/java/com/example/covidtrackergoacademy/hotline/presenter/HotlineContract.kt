package com.example.covidtrackergoacademy.hotline.presenter

import com.example.covidtrackergoacademy.hotline.data.HotlineData

interface HotlineContract {
    interface View{
        fun updateData(data : MutableList<HotlineData>)
        fun showError(error: String)
    }
    interface Presenter{
        fun getData()
    }
}