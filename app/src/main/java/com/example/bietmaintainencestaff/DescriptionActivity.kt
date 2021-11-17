package com.example.bietmaintainencestaff

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
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
    private var requestById: String? = ""
    private var hostel_Name: String = ""
    private var room_num: String = ""
    private var requested_By: String = ""
    private var problem_request: String = ""
    private var phone_num: String = ""
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        hostelName = findViewById(R.id.hostel_name)
        roomNo = findViewById(R.id.room_num)
        requestedBy = findViewById(R.id.req_By)
        problem = findViewById(R.id.textView10)
        btnApprove = findViewById(R.id.btnApproveRequest)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)
        requestId = intent.getStringExtra("reqId").toString()
        requestById = intent.getStringExtra("reqById").toString()
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
                .child(hostel_Name)

        databaseRef =
            FirebaseDatabase.getInstance().reference.child("Students").child(requestById!!)
                .child("Requests").child("OngoingRequests").child(requestId!!)
        databaseReference.child("PendingRequests").child(requestId!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    hostel_Name = snapshot.child("hostelName").value.toString()
                    room_num = snapshot.child("roomNo").value.toString()
                    requested_By = snapshot.child("requestedBy").value.toString()
                    problem_request = snapshot.child("problem").value.toString()
                    phone_num = snapshot.child("mobNo").value.toString()
                    hostelName.text = hostel_Name
                    roomNo.text = "Room No-" + room_num
                    requestedBy.text = requested_By
                    problem.text = problem_request
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        btnApprove.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog = builder.show()
            builder.setTitle("Confirmation")
            builder.setMessage("Are you sure that you want to approve the request")
            builder.setPositiveButton("Yes") { text, listener ->
                dialog.dismiss()
                progressDialog.setMessage("Approving the request")
                progressDialog.show()
                val statusHash = HashMap<String, Any>()
                statusHash["status"] = "Approved"
                databaseReference.child("PendingRequests").child(requestId!!).removeValue()
                    .addOnSuccessListener {
                        val requestHashMap = HashMap<String, Any>()
                        requestHashMap["requestId"] = requestId!!
                        requestHashMap["roomNo"] = room_num
                        requestHashMap["requestedBy"] = requested_By
                        requestHashMap["problem"] = problem_request.toString()
                        requestHashMap["hostelName"] = hostelName
                        requestHashMap["mobNo"] = phone_num
                        requestHashMap["reqById"] = requestById!!
                        requestHashMap["status"] = "Ongoing"

                        databaseReference.child("OngoingRequests").child(requestId!!)
                            .updateChildren(requestHashMap).addOnSuccessListener {
                                databaseRef.updateChildren(statusHash)
                                progressDialog.dismiss()
                                startActivity(Intent(this, ActivityPendingRequests::class.java))
                            }
                    }
            }
            builder.setNegativeButton("No") { text, listener ->
                dialog.dismiss()
            }

        }
    }
}