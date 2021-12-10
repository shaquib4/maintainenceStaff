package com.example.bietmaintainencestaff

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class Home : AppCompatActivity() {
    private lateinit var addStaff: TextView
    private lateinit var allStaffs: TextView
    private lateinit var rlPending: RelativeLayout
    private lateinit var rlInProgress: RelativeLayout
    private lateinit var rlCompleted: RelativeLayout
    private lateinit var pendingRequests: TextView
    private lateinit var inProgressRequests: TextView
    private lateinit var completedRequests: TextView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var chooseHostel: Spinner
    private var pendingCnt: String = ""
    private var ongoingCnt: String = ""
    private var completedCnt: String = ""
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addStaff = findViewById(R.id.add_staff)
        allStaffs = findViewById(R.id.allStaffs)
        rlPending = findViewById(R.id.rl)
        rlInProgress = findViewById(R.id.rl1)
        rlCompleted = findViewById(R.id.rl2)
        pendingRequests = findViewById(R.id.pendingCount)
        inProgressRequests = findViewById(R.id.inProgressCount)
        completedRequests = findViewById(R.id.completedCount)
        chooseHostel = findViewById(R.id.sp1)
        sharedPreferences = getSharedPreferences("Shared_Prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
        pendingRequests.text = pendingCnt
        inProgressRequests.text = ongoingCnt
        completedRequests.text = completedCnt
        val hostelArray = arrayOf(
            "Vrindavan Bhawan",
            "Saket Bhawan",
            "Pancvati Bhawan",
            "Atal Bihari Bhawan",
            "Rajendra Agnihotri Bhawan",
            "Yashodhra Bhawan",
            "Kalpana Chawla Bhawan"
        )
        chooseHostel.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, hostelArray)
        chooseHostel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                showRequestsCount(hostelArray[position])
                editor.putString("hostel", hostelArray[position])
                editor.apply()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        addStaff.setOnClickListener {
            startActivity(Intent(this, AddYourStaff::class.java))
        }
        allStaffs.setOnClickListener {

        }
        rlPending.setOnClickListener {
            val intent=Intent(this, ActivityPendingRequests::class.java)
            intent.putExtra("hostel",chooseHostel.selectedItem.toString())
            startActivity(intent)
        }
        rlCompleted.setOnClickListener {
            startActivity(Intent(this, ActivityCompletedRequests::class.java))
        }
        rlInProgress.setOnClickListener {
            startActivity(Intent(this, ActivityOngoingRequests::class.java))
        }
    }

    private fun showRequestsCount(hostel: String) {
        val databaseRef =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
                .child(hostel)
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pendingCnt = snapshot.child("PendingRequests").childrenCount.toString()
                ongoingCnt = snapshot.child("OngoingRequests").childrenCount.toString()
                completedCnt = snapshot.child("CompletedRequests").childrenCount.toString()
                pendingRequests.text = pendingCnt
                inProgressRequests.text = ongoingCnt
                completedRequests.text = completedCnt
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onStart() {
        super.onStart()
        sharedPreferences = getSharedPreferences("Shared_Prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val hostel_name = sharedPreferences.getString("hostel", "")
        if (hostel_name.toString() == "") {
            editor.putString("hostel", "Vrindavan Bhawan")
            editor.apply()
            val databaseRef =
                FirebaseDatabase.getInstance().reference.child("Staff").child("JE")
                    .child("Requests")
                    .child("Vrindavan Bhawan")
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    pendingCnt = snapshot.child("PendingRequests").childrenCount.toString()
                    ongoingCnt = snapshot.child("OngoingRequests").childrenCount.toString()
                    completedCnt = snapshot.child("CompletedRequests").childrenCount.toString()

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        } else {
            val databaseRef =
                FirebaseDatabase.getInstance().reference.child("Staff").child("JE")
                    .child("Requests")
                    .child(hostel_name.toString())
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    pendingCnt = snapshot.child("PendingRequests").childrenCount.toString()
                    ongoingCnt = snapshot.child("OngoingRequests").childrenCount.toString()
                    completedCnt = snapshot.child("CompletedRequests").childrenCount.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }

    }
}