package com.example.bietmaintainencestaff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var edtMobileNumber:EditText
    private lateinit var btnSubmit:Button
    private lateinit var databaseReference: DatabaseReference
    private var reqPhone=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edtMobileNumber=findViewById(R.id.editTextPhone)
        btnSubmit=findViewById(R.id.buttonSubmit)
        databaseReference=FirebaseDatabase.getInstance().reference.child("Staff").child("JE")
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                reqPhone=snapshot.child("phone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        btnSubmit.setOnClickListener {
            if(edtMobileNumber.text.toString()==reqPhone){
                val intent=Intent(this,MainActivity::class.java)
                intent.putExtra("phone",reqPhone)
                startActivity(intent)
            }else{
                Toast.makeText(this,"You are not eligible for this",Toast.LENGTH_SHORT).show()
            }
        }

    }
}