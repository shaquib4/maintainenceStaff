package com.example.bietmaintainencestaff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bietmaintainencestaff.Adapters.AdapterAllStaff
import com.example.bietmaintainencestaff.Modals.ModalStaff
import com.google.firebase.database.*

class ActivityAllStaff : AppCompatActivity() {
    private lateinit var databaseref:DatabaseReference
    private lateinit var adapterStaff:AdapterAllStaff
    private lateinit var recycler:RecyclerView
    private  lateinit var staffList:ArrayList<ModalStaff>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_staff)
        staffList=ArrayList<ModalStaff>()
        recycler=findViewById(R.id.staffRecycler)
        recycler.layoutManager=LinearLayoutManager(this)
        databaseref=FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Assistants")
        databaseref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                staffList.clear()
                for (i in snapshot.children){
                    val obj=ModalStaff(
                            i.child("uid").toString(),
                            i.child("name").toString(),
                            i.child("phone").toString()
                    )
                    staffList.add(obj)
                    adapterStaff=AdapterAllStaff(this@ActivityAllStaff,staffList)
                    recycler.adapter=adapterStaff
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}