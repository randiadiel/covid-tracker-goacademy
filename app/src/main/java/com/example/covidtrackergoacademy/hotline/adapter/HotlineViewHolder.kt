package com.example.covidtrackergoacademy.hotline.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.hotline.data.HotlineData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hotline.view.*

class HotlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: HotlineData){
        itemView.tv_item_hotline_name.text = data.name
        itemView.tv_item_hotline_phone.text = data.phone
        if(data.imgIcon.isNotBlank()) Picasso.get().load(data.imgIcon).placeholder(R.drawable.progress).into(itemView.iv_item_hotline_image)
        itemView.btn_item_hotline_call.setOnClickListener{
            actionDial(data.phone,itemView.context)
        }
    }
    private fun actionDial(phoneNumber : String = "911", context: Context) {
        val intent = Intent().apply{
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }
}