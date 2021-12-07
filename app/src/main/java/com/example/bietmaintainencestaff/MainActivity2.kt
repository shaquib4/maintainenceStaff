package com.example.bietmaintainencestaff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bietmaintainencestaff.Modals.ModalSNumbers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity2 : AppCompatActivity() {
    private lateinit var edtMobileNumber:EditText
    private lateinit var btnSubmit:Button
    private lateinit var databaseReference: DatabaseReference
    private lateinit var staffNumbers:ArrayList<ModalSNumbers>
    private var reqPhone=""
    private  var name:String=""
    private  var staffUid:String=""
    private var bool :Boolean=false
    private var staffPhone:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edtMobileNumber=findViewById(R.id.editTextPhone)
        btnSubmit=findViewById(R.id.buttonSubmit)
        staffNumbers=ArrayList<ModalSNumbers>()
        databaseReference=FirebaseDatabase.getInstance().reference.child("Staff").child("JE")
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                reqPhone=snapshot.child("phone").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

       for (i in staffNumbers){
           if (i.phone==edtMobileNumber.text.toString()){

           }
       }

        btnSubmit.setOnClickListener {
            Log.e("phone","$reqPhone")
            databaseReference.child("Assistants").addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        staffPhone=i.child("phone").value.toString()
                        Toast.makeText(this@MainActivity2,staffPhone,Toast.LENGTH_SHORT).show()
                        if (edtMobileNumber.text.toString()==staffPhone){
                            bool=true
                            name=i.child("name").value.toString()
                            staffUid=i.child("uid").value.toString()
                        }

                    }
                    when {
                        edtMobileNumber.text.toString()==reqPhone -> {
                            val intent=Intent(this@MainActivity2,MainActivity::class.java)
                            intent.putExtra("phone",reqPhone)
                            startActivity(intent)
                        }
                        bool -> {
                            val intent=Intent(this@MainActivity2,MainActivity::class.java)
                            intent.putExtra("phone",staffPhone)
                            intent.putExtra("name",name)
                            intent.putExtra("uid",staffUid)
                            intent.putExtra("bool",bool)
                            startActivity(intent)
                        }
                        else -> {
                            Toast.makeText(this@MainActivity2,"You are not eligible for this",Toast.LENGTH_SHORT).show()
                        }
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }
            })


        }

    }

   public override fun onStart() {
        super.onStart()
        val user= FirebaseAuth.getInstance().currentUser
        if (user!=null){
            startActivity(Intent(this,Home::class.java))
            finish()
        }
    }
}