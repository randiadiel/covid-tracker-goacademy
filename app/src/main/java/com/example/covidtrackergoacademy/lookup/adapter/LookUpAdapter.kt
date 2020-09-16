package com.example.covidtrackergoacademy.lookup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.data.LookUpData

class LookUpAdapter(private val LookUpList: MutableList<LookUpData>) : RecyclerView.Adapter<LookUpViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookUpViewHolder {
        return LookUpViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_look_up, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LookUpViewHolder, position: Int) {
        holder.bind(LookUpList[position])
    }

    override fun getItemCount(): Int {
        return LookUpList.size
    }

    fun updateData(newList: List<LookUpData>){
        LookUpList.clear()
        LookUpList.addAll(newList)

        notifyDataSetChanged()
    }
}