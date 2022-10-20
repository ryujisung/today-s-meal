package com.example.todaysschoolmeal

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class haveaccountActivity : AppCompatActivity() {

    var viewList = arrayListOf<View>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haveaccount)

        findViewById<Button>(R.id.log_btn).setOnClickListener {
            val loginintent = Intent(this,SignInActivity::class.java)

            startActivity(loginintent)
        }

        findViewById<ImageView>(R.id.tv_signup).setOnClickListener {
            val signupintent = Intent(this, signupActivity::class.java)

            startActivity(signupintent)
        }
    }
}