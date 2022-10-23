package com.example.todaysschoolmeal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import kotlinx.coroutines.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class schoolsearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schoolsearch)


        findViewById<TextView>(R.id.school).addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val userhighschool: EditText = findViewById(R.id.school)

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .baseUrl("https://jrady721.cafe24.com/")
                    .build()

                val schoolapiservice = retrofit.create(schoolsearchService::class.java)

                CoroutineScope(Dispatchers.IO).launch{
                    val result = schoolapiservice.ApiService(userhighschool.toString())
                    Log.d("apiResult", result.toString())
                    val a =0;
                    val SchoolData = mutableListOf<School>(
                        School(
                            name = result.schools[a].name.toString(),
                            code = result.schools[0].code.toString(),
                            office = result.schools[0].office.toString(),
                            level = result.schools[0].level.toInt(),
                            address = result.schools[0].address.toString(),
                        ),
                    )
                    findViewById<RecyclerView>(R.id.list_main).apply {
                        adapter = SchoolAdapter(result.schools)
                        layoutManager = LinearLayoutManager(this@schoolsearchActivity)
                    }
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }
}