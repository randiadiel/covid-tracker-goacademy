package com.example.covidtrackergoacademy.lookup.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.lookup.LookUpData
import kotlinx.android.synthetic.main.item_look_up.view.*

class LookUpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: LookUpData){
        itemView.tv_look_up_province_name.text = data.provinceName
        itemView.tv_look_up_positive_cases.text = "${data.numberOfPositiveCases}"
    }
}