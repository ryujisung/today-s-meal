package com.example.todaysschoolmeal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.file.Files.list
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewview = inflater.inflate(R.layout.fragment_home, container, false)
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        viewview.findViewById<TextView>(R.id.school).setOnClickListener {
            var intent = Intent(context,schoolsearchActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        viewview.findViewById<ImageButton>(R.id.star).setOnClickListener {
            Toast.makeText(context, "당신은 별로에요. 내마음의 별로...q(≧▽≦q)", Toast.LENGTH_LONG).show()
        }



        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://schoolmenukr.ml/")
            .build()

        val mealApiService = retrofit.create(MealApiService::class.java)
        val monthlist : List<Int> = listOf(31,28,31,30,31,30,31,31,30,31,30,31)
        CoroutineScope(Dispatchers.IO).launch{
            val currenttime = LocalDateTime.now();
            val date = currenttime.dayOfMonth.toInt()
            val month = currenttime.monthValue.toInt()
            val result = mealApiService.getMeal("B100000658")

            withContext(Dispatchers.Main){
                viewview.findViewById<TextView>(R.id.firstmeal).text = "${month}월 ${date}일의 급식"
                viewview.findViewById<TextView>(R.id.firstmeal_menu).text = result.menu[date-1].lunch.toString()
                if(monthlist[month-1]>=date+1){
                    viewview.findViewById<TextView>(R.id.secondmeal).text = "${currenttime.plusDays(1).monthValue}월 ${currenttime.plusDays(1).dayOfMonth}일의 급식"
                    viewview.findViewById<TextView>(R.id.secondmeal_menu).text = result.menu[date].lunch.toString()
                }
                if(monthlist[month-1]>=date+2) {
                    viewview.findViewById<TextView>(R.id.thirdmeal).text =
                        "${currenttime.plusDays(2).monthValue}월 ${currenttime.plusDays(2).dayOfMonth}일의 급식"
                    viewview.findViewById<TextView>(R.id.thirdmeal_menu).text =
                        result.menu[date + 1].lunch.toString()
                }
            }
        }
        return viewview
    }

}