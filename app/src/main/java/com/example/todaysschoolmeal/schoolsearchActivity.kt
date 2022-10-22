package com.example.todaysschoolmeal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class schoolsearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schoolsearch)


        findViewById<TextView>(R.id.school).addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val userhighschool: EditText = findViewById(R.id.school)

                var retrofit = Retrofit.Builder()
                    .baseUrl(userhighschool.toString())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                val service = retrofit.create(schoolsearchService::class.java)


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

        fun createOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            return builder.build()
        }

        fun setRetrofit(){

            val retrofit = Retrofit.Builder()
                .baseUrl("http://jrady721.cafe24.com/api/school/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()

            val service = retrofit.create(schoolsearchService::class.java)

            val call: Call<School> = service.ApiService()
            call.enqueue(object : Callback<School> {
                override fun onFailure(call: Call<School>, t: Throwable) {
                    Toast.makeText(applicationContext,"실패",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<School>, response: Response<School>) {
                    if(response.body()!=null){
                        Toast.makeText(applicationContext,"성공",Toast.LENGTH_SHORT).show()
                        text = response.body()!!.date + " 입니다"

                    }
                }

            })

        }




    }


}