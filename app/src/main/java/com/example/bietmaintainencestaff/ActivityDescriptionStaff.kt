package com.example.bietmaintainencestaff

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityDescriptionStaff : AppCompatActivity() {
    private lateinit var hostelNameStaff: TextView
    private lateinit var studentName: TextView
    private lateinit var studentRoomNo: TextView
    private lateinit var studentProblem: TextView
    private var radioGroup: RadioGroup? = null
    private lateinit var edtMaterialDesc: EditText
    private lateinit var btnSubmit: Button
    private lateinit var progressDialog: ProgressDialog
    private lateinit var databaseReference: DatabaseReference
    private lateinit var databaseRef: DatabaseReference
    private lateinit var callStudent: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_staff)
        hostelNameStaff = findViewById(R.id.hostel_name_staff)
        studentName = findViewById(R.id.req_By_student)
        studentRoomNo = findViewById(R.id.room_num_student)
        studentProblem = findViewById(R.id.problem_student)
        radioGroup = findViewById(R.id.radioRequest)
        edtMaterialDesc = findViewById(R.id.material_desc)
        btnSubmit = findViewById(R.id.btnSubmitRequest)
        callStudent = findViewById(R.id.phoneCall)
        progressDialog = ProgressDialog(this)
        progressDialog.setCanceledOnTouchOutside(false)
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Requests")
                .child("hostelName")
        databaseRef = FirebaseDatabase.getInstance().reference.child("Students").child("studentUid")
            .child("Requests")
        btnSubmit.setOnClickListener {
            val id = radioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(id)
            when (radioButton.text) {
                "हां" -> {
                    edtMaterialDesc.isEnabled = true
                    if (TextUtils.isEmpty(edtMaterialDesc.text.toString())) {
                        edtMaterialDesc.error = "कृपया सामग्री का वर्णन करे"
                        return@setOnClickListener
                    } else {


                    }
                }
                "नहीं" -> {
                    edtMaterialDesc.isEnabled = false
                    val builder = AlertDialog.Builder(this)
                    val dialog = builder.show()
                    builder.setTitle("पुष्टीकरण")
                    builder.setMessage("क्या आप सुनिश्चित हैं कि काम सफलतापूर्वक पूरा हो गया है")
                    builder.setPositiveButton("हां") { text, listener ->
                        dialog.dismiss()
                        progressDialog.setMessage("कृपया प्रतीक्षा करें")
                        progressDialog.show()
                        databaseReference.child("OngoingRequests").child("requestId").removeValue()
                            .addOnSuccessListener {
                                databaseReference.child("CompletedRequests")
                            }


                    }
                    builder.setNegativeButton("No") { text, listener ->
                        dialog.dismiss()
                    }
                }
            }
        }
        callStudent.setOnClickListener {
            makePhoneCallStudent()
        }
    }

    private fun makePhoneCallStudent() {
        //val number = customerMobileNo
        val intent = Intent(Intent.ACTION_DIAL)
        //intent.data = Uri.parse("tel:$number")
        val chooser = Intent.createChooser(intent, "Call Using")
        startActivity(chooser)
    }
}