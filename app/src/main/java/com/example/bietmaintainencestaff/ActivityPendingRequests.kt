package com.example.bietmaintainencestaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.google.firebase.database.*

class ActivityPendingRequests : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var listPendingRequests: List<ModalPendingRequest>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests)
        listPendingRequests = ArrayList<ModalPendingRequest>()
        databaseReference = FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
        databaseReference.child("PendingRequests").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                (listPendingRequests as ArrayList<ModalPendingRequest>).clear()
                for (i in snapshot.children) {
                    val obj = ModalPendingRequest(
                        i.child("requestId").value.toString(),
                        i.child("roomNo").value.toString(),
                        i.child("requestedBy").value.toString(),
                        i.child("problem").value.toString(),
                        i.child("hostelName").value.toString(),
                        i.child("mobNo").value.toString(),
                        i.child("status").value.toString(),
                    )
                    (listPendingRequests as ArrayList<ModalPendingRequest>).add(obj)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}