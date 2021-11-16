package com.example.bietmaintainencestaff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Adapters.AdapterOngoingRequests
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.google.firebase.database.*

class ActivityOngoingRequests : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var listOngoingRequests: List<ModalPendingRequest>
    private lateinit var recyclerViewOngoing: RecyclerView
    private lateinit var ongoingRequestAdapter: AdapterOngoingRequests
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongoing_requests)
        listOngoingRequests = ArrayList<ModalPendingRequest>()
        //recyclerViewPending=findViewById()
        recyclerViewOngoing.layoutManager = LinearLayoutManager(this)
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
        databaseReference.child("OngoingRequests").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}