package com.example.bietmaintainencestaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class AddYourStaff : AppCompatActivity() {
    private lateinit var edtStaffNo:EditText
    private lateinit var edtStaffName:EditText
    private lateinit var btnConfirm:Button
    private lateinit var databaseReference: DatabaseReference
    private var timestamp:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_your_staff)
        edtStaffNo=findViewById(R.id.edtStaffNumber)
        edtStaffName=findViewById(R.id.edtStaffName)
        btnConfirm=findViewById(R.id.btnConfirmStaff)
        timestamp=System.currentTimeMillis().toString()
        databaseReference=FirebaseDatabase.getInstance().reference.child("Staff").child("JE")
        btnConfirm.setOnClickListener {
            when {
                TextUtils.isEmpty(edtStaffNo.text.toString())->{
                    edtStaffNo.error="Please Enter Staff Mobile Number"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(edtStaffName.text.toString())->{
                    edtStaffName.error="Please Enter Staff Name"
                    return@setOnClickListener
                }
                else->{
                    val staffMap=HashMap<String,Any>()
                    staffMap["id"]=timestamp
                    staffMap["name"]=edtStaffName.text.toString()
                    staffMap["mobNo"]=edtStaffNo.text.toString()
                    databaseReference.child("Assistants").child(timestamp).updateChildren(staffMap).addOnSuccessListener {
                        Toast.makeText(this,"Staff Added Successfully",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}