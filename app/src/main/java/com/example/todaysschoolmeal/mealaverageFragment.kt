package com.example.todaysschoolmeal

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime


class mealaverageFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewview = inflater.inflate(R.layout.fragment_mealaverage, container, false)
        val currenttime = LocalDateTime.now();
        val date = currenttime.dayOfMonth.toInt()
        val month = currenttime.monthValue.toInt()
        viewview.findViewById<TextView>(R.id.today).text = "${month}월 ${date}일의 급식평가"
        viewview.findViewById<AppCompatButton>(R.id.pengga).setOnClickListener {
            val ratingstar = viewview.findViewById<RatingBar>(R.id.ratingbar).rating
            
        }
        return viewview
    }

}