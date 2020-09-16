package com.example.covidtrackergoacademy.lookup.mvp

import android.app.Activity
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.lookup.data.LookUpData

interface LookUpContract {
    interface View {
        fun updateData(data : MutableList<LookUpData>)
        fun showError(error : String)
    }

    interface Presenter {
        fun getData()
        fun search(et : EditText, cancelButton : ImageButton, rv : RecyclerView)
        fun backButton(context : Activity, ib : ImageButton)
    }
}