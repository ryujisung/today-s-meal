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
                    val result = schoolapiservice.ApiService()
                    Log.d("apiResult", result.toString())
                    val menu : List<School> = result.schools

                    val SchoolData = mutableListOf<School>(
                        School(
                            name = result.schools[0].name.toString(),
                            code = result.schools[0].code.toString(),
                            office = result.schools[0].office.toString(),
                            level = result.schools[0].level.toInt(),
                            address = result.schools[0].address.toString(),
                        ),
                        School(
                            name = result.schools[1].name.toString(),
                            code = result.schools[1].code.toString(),
                            office = result.schools[1].office.toString(),
                            level = result.schools[1].level.toInt(),
                            address = result.schools[1].address.toString(),
                        ),
                        School(
                            name = result.schools[2].name.toString(),
                            code = result.schools[2].code.toString(),
                            office = result.schools[2].office.toString(),
                            level = result.schools[2].level.toInt(),
                            address = result.schools[2].address.toString(),
                        ),
                        School(
                            name = result.schools[3].name.toString(),
                            code = result.schools[3].code.toString(),
                            office = result.schools[3].office.toString(),
                            level = result.schools[3].level.toInt(),
                            address = result.schools[3].address.toString(),
                        ),
                        School(
                            name = result.schools[4].name.toString(),
                            code = result.schools[4].code.toString(),
                            office = result.schools[4].office.toString(),
                            level = result.schools[4].level.toInt(),
                            address = result.schools[4].address.toString(),
                        ),
                        School(
                            name = result.schools[5].name.toString(),
                            code = result.schools[5].code.toString(),
                            office = result.schools[5].office.toString(),
                            level = result.schools[5].level.toInt(),
                            address = result.schools[5].address.toString(),
                        ),
                        School(
                            name = result.schools[6].name.toString(),
                            code = result.schools[6].code.toString(),
                            office = result.schools[6].office.toString(),
                            level = result.schools[6].level.toInt(),
                            address = result.schools[6].address.toString(),
                        ),
                        School(
                            name = result.schools[7].name.toString(),
                            code = result.schools[7].code.toString(),
                            office = result.schools[7].office.toString(),
                            level = result.schools[7].level.toInt(),
                            address = result.schools[7].address.toString(),
                        ),
                        School(
                            name = result.schools[8].name.toString(),
                            code = result.schools[8].code.toString(),
                            office = result.schools[8].office.toString(),
                            level = result.schools[8].level.toInt(),
                            address = result.schools[8].address.toString(),
                        ),
                        School(
                            name = result.schools[9].name.toString(),
                            code = result.schools[9].code.toString(),
                            office = result.schools[9].office.toString(),
                            level = result.schools[9].level.toInt(),
                            address = result.schools[9].address.toString(),
                        ),
                    )
                    findViewById<RecyclerView>(R.id.list_main).apply {
                        adapter = SchoolAdapter(SchoolData)
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