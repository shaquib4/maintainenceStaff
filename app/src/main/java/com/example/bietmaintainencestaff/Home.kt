package com.example.bietmaintainencestaff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Home : AppCompatActivity() {
    private  lateinit var addStaff:TextView
    private lateinit var allStaffs:TextView
    private lateinit var rlPending:RelativeLayout
    private lateinit var rlInProgress:RelativeLayout
    private lateinit var rlCompleted:RelativeLayout
    private lateinit var pendingRequests:TextView
    private lateinit var inProgressRequests:TextView
    private lateinit var completedRequests:TextView
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addStaff=findViewById(R.id.add_staff)
        allStaffs=findViewById(R.id.allStaffs)
        rlPending=findViewById(R.id.rl)
        rlInProgress=findViewById(R.id.rl1)
        rlCompleted=findViewById(R.id.rl2)
        pendingRequests=findViewById(R.id.pendingCount)
        inProgressRequests=findViewById(R.id.inProgressCount)
        completedRequests=findViewById(R.id.completedCount)
        databaseReference=FirebaseDatabase.getInstance().reference.child("Staff")
        addStaff.setOnClickListener {
            startActivity(Intent(this,AddYourStaff::class.java))
        }
        allStaffs.setOnClickListener {

        }
        rlPending.setOnClickListener {
            startActivity(Intent(this,ActivityPendingRequests::class.java))
        }
        rlCompleted.setOnClickListener {
            startActivity(Intent(this,ActivityCompletedRequests::class.java))
        }
        rlInProgress.setOnClickListener {
            startActivity(Intent(this,ActivityOngoingRequests::class.java))
        }
    }
}