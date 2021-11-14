package com.example.bietmaintainencestaff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    private lateinit var edtMobileNumber:EditText
    private lateinit var btnSubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edtMobileNumber=findViewById(R.id.editTextPhone)
        btnSubmit=findViewById(R.id.buttonSubmit)
        btnSubmit.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("phone",edtMobileNumber.text.toString())
            startActivity(intent)
        }
      /*  implementation 'com.chaos.view:pinview:1.4.4'*/
    }
}