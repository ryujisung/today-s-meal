package com.example.todaysschoolmeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class signupActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            sigininAndSignup()
        }

        findViewById<ImageView>(R.id.tv_login).setOnClickListener {
            sigininAndSignup()
            Toast.makeText(this, "성공!", Toast.LENGTH_SHORT).show()
            val loginintent = Intent(this,MainActivity::class.java)
            startActivity(loginintent)
        }
        findViewById<TextView>(R.id.school).setOnClickListener {
            val schoolintent = Intent(this,schoolsearchActivity::class.java)
            startActivity(schoolintent)
        }
    }
    fun sigininAndSignup(){
        val ao: EditText = findViewById(R.id.pn)
        val aw: EditText = findViewById(R.id.pw)
        val awc: EditText = findViewById(R.id.pwcheck)
        if (aw.text.toString() == awc.text.toString()){
            auth?.createUserWithEmailAndPassword(ao.text.toString(),aw.text.toString())
                ?.addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        MovemainPage(task.result?.user)
                    }else if(task.exception?.message.isNullOrEmpty()){
                        Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                    }else{
                        sigininEmail()
                    }
                }
        }else{
            Toast.makeText(this,"비밀번호가 맞지않습니다. 다시 입력해주세요",Toast.LENGTH_LONG).show()
        }
    }
    fun sigininEmail(){
        val ao: EditText = findViewById(R.id.pn)
        val aw: EditText = findViewById(R.id.pw)
        auth?.createUserWithEmailAndPassword(ao.text.toString(),aw.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    MovemainPage(task.result?.user)
                }else{
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    fun MovemainPage(user: FirebaseUser?){ //task람다 형식으로 로그인
        if(user != null){
            Toast.makeText(this, "성공!", Toast.LENGTH_SHORT).show()
            val loginintent = Intent(this,MainActivity::class.java)
            startActivity(loginintent)
        }
    }
}