package com.example.covidtrackergoacademy.hotline.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.hotline.HotlineData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_look_up.view.*

class HotlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(data: HotlineData){
        // itemView.tv_look_up_province_name.text = data.provinceName
        itemView.tv_look_up_province_name.text = data.name
        
        Picasso.get().load(data.imgIcon).into(itemView.)
    }
}