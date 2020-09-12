package com.example.covidtrackergoacademy.hotline.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.hotline.HotlineData

class HotlineAdapter(private val HotlineList: MutableList<HotlineData>) : RecyclerView.Adapter<HotlineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotlineViewHolder {
        return HotlineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_look_up, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HotlineViewHolder, position: Int) {
        holder.bind(HotlineList[position])
    }

    override fun getItemCount(): Int {
        return HotlineList.size
    }

    fun updateData(newList: List<HotlineData>){
        HotlineList.clear()
        HotlineList.addAll(newList)

        notifyDataSetChanged()
    }
}