package com.example.turkiye_appv01.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.turkiye_appv01.R
import com.example.turkiye_appv01.Sehir

class SehirAdapter(private val dataset: List<Sehir>): RecyclerView.Adapter<SehirAdapter.SehirHolder>()  {

    class SehirHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val sehirad: Button = itemview.findViewById(R.id.sehirbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SehirHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sehirrows, parent, false)
        return SehirHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: SehirHolder, position: Int) {
        val city = dataset[position]
        holder.sehirad.text = city.sehirad
    }

}