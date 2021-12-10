package com.example.bietmaintainencestaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Adapters.AdapterPendingRequests
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.google.firebase.database.*

class ActivityPendingRequests : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var listPendingRequests: List<ModalPendingRequest>
    private lateinit var recyclerViewPending: RecyclerView
    private lateinit var pendingRequestAdapter: AdapterPendingRequests
    private  var hostelName:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_requests)
        hostelName=intent.getStringExtra("hostel").toString()
        listPendingRequests = ArrayList<ModalPendingRequest>()
        recyclerViewPending=findViewById(R.id.rvPending)
        recyclerViewPending.layoutManager = LinearLayoutManager(this)
        Toast.makeText(this,hostelName.toString(),Toast.LENGTH_SHORT).show()
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests").child(hostelName!!)
        databaseReference.child("PendingRequests")
            .addValueEventListener(object : ValueEventListener {
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
                            i.child("reqById").value.toString()
                        )
                        (listPendingRequests as ArrayList<ModalPendingRequest>).add(obj)
                    }
                    pendingRequestAdapter =
                        AdapterPendingRequests(this@ActivityPendingRequests, listPendingRequests, hostelName!!)
                    recyclerViewPending.adapter = pendingRequestAdapter
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}