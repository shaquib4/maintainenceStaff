package com.example.bietmaintainencestaff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Home : AppCompatActivity() {
    private  lateinit var addStaff:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addStaff=findViewById(R.id.add_staff)
        addStaff.setOnClickListener {
            startActivity(Intent(this,AddYourStaff::class.java))
        }
    }
}