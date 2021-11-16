package com.example.bietmaintainencestaff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Adapters.AdapterCompletedRequests
import com.example.bietmaintainencestaff.Modals.ModalPendingRequest
import com.google.firebase.database.*

class ActivityCompletedRequests : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var listCompletedRequests: List<ModalPendingRequest>
    private lateinit var recyclerViewCompleted: RecyclerView
    private lateinit var completedRequestAdapter: AdapterCompletedRequests
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_requests)
        listCompletedRequests = ArrayList<ModalPendingRequest>()
        //recyclerViewPending=findViewById()
        recyclerViewCompleted.layoutManager = LinearLayoutManager(this)
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
        databaseReference.child("CompletedRequests").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}