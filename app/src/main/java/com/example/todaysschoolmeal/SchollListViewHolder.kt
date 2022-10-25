package com.example.todaysschoolmeal

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SchollListViewHolder(private val row : View) : RecyclerView.ViewHolder(row) {
    val title : TextView = row.findViewById(R.id.txt_Schoolname_title)
    val Schooladdress: TextView = row.findViewById(R.id.txt_Schooladdres_addres)

    fun onBind(SchoolData : School){
        title.text = SchoolData.name
        Schooladdress.text = SchoolData.address


        row.setOnClickListener {
            val db = Firebase.firestore
            //val user = auth?.currentUser
            db.collection("user")
                .document(FirebaseAuth.getInstance().uid!!)
                .set(hashMapOf("schoolname" to "${SchoolData.name}",
                    "schoolcode" to "${SchoolData.code}"))
            //Todo 클릭했을때 파이어베이스에 유저의 학교코드와 학교를 저장
        }
    }
}