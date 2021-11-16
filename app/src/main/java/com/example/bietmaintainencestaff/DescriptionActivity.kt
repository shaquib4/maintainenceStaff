package com.example.bietmaintainencestaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*

class DescriptionActivity : AppCompatActivity() {
    private lateinit var hostelName: TextView
    private lateinit var roomNo: TextView
    private lateinit var requestedBy: TextView
    private lateinit var problem: TextView
    private lateinit var btnApprove: Button
    private lateinit var databaseReference: DatabaseReference
    private lateinit var databaseRef: DatabaseReference
    private var requestId: String? = ""
    private var hostel_Name: String = ""
    private var room_num: String = ""
    private var requested_By: String = ""
    private var problem_request: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        hostelName = findViewById(R.id.hostel_name)
        roomNo = findViewById(R.id.room_num)
        requestedBy = findViewById(R.id.req_By)
        problem = findViewById(R.id.textView10)
        btnApprove = findViewById(R.id.btnApproveRequest)
        requestId = intent.getStringExtra("reqId").toString()
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
                .child(hostel_Name)
                .child("PendingRequests").child(requestId!!)
        databaseRef = FirebaseDatabase.getInstance().reference.child("Students")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hostel_Name = snapshot.child("hostelName").value.toString()
                room_num = snapshot.child("roomNo").value.toString()
                requested_By = snapshot.child("requestedBy").value.toString()
                problem_request = snapshot.child("problem").value.toString()
                hostelName.text = hostel_Name
                roomNo.text = "Room No-" + room_num
                requestedBy.text = requested_By
                problem.text = problem_request
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        btnApprove.setOnClickListener {

        }
    }
}