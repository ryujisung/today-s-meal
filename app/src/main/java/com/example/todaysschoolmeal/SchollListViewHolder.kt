package com.example.todaysschoolmeal

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SchollListViewHolder(private val row : View) : RecyclerView.ViewHolder(row) {
    val title : TextView = row.findViewById(R.id.txt_Schoolname_title)
    val Schooladdress: TextView = row.findViewById(R.id.txt_Schooladdres_addres)

    fun onBind(SchoolData : School){
        title.text = SchoolData.name
        Schooladdress.text = SchoolData.address


        row.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SchoolData.name))
            row.context.startActivity(intent)
        }
    }
}