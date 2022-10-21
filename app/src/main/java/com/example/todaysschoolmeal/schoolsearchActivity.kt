package com.example.todaysschoolmeal

import android.media.DrmInitData.SchemeInitData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



class schoolsearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schoolsearch)
        data class data(val status: Int ,val schools: String)

        findViewById<TextView>(R.id.school).addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val userhighschool: EditText = findViewById(R.id.school)

                val retrofit = Retrofit.Builder()
                    .baseUrl("적어줄 baseUrl ")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createOkHttpClient())
                    .build()

                val service = retrofit.create(UserService::class.java)


                val SchoolData = mutableListOf<School>(

                )
                findViewById<RecyclerView>(R.id.list_main).apply {
                    adapter = SchoolAdapter(SchoolData)
                    layoutManager = LinearLayoutManager(this@schoolsearchActivity)
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }
        })


    }


}