package com.example.todaysschoolmeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        auth = FirebaseAuth.getInstance()
        Log.d("ddddddd",auth.currentUser.toString());
        var handler = Handler()
        handler.postDelayed({
            if(auth.currentUser != null){
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else {
                var intent = Intent(this, haveaccountActivity::class.java)
                startActivity(intent)
            }
        },1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}