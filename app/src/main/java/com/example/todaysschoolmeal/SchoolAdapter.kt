package com.example.todaysschoolmeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SchoolAdapter(val datas : List<School>) : RecyclerView.Adapter<SchollListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchollListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_school_list_item, parent, false)
        return SchollListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchollListViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}