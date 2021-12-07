package com.example.bietmaintainencestaff

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private  lateinit var dataReference:DatabaseReference
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var storedVerificationId: String
    var firebaseUser: FirebaseUser? = null
    private var phone:String?="400"
    private var staffName:String?=null
    private var staffUid:String?=null
    private var nBool:Boolean?=null
    private lateinit var otp:PinView
    private lateinit var progress_verify:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        otp=findViewById(R.id.otp)
        progress_verify=findViewById(R.id.progress_verify)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser
        phone=intent.getStringExtra("phone")
        staffName=intent.getStringExtra("name")
        staffUid=intent.getStringExtra("uid")
        nBool=intent.getBooleanExtra("bool",false)

        sendVerification("+91$phone")
        progress_verify.visibility = View.VISIBLE

    }

    private fun sendVerification(phone: String) {
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, callbacks)
    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            val code = credential.smsCode
            if (code != null) {

                verifyVerification(code)
                otp.setText(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(applicationContext, "" + e.message, Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                Toast.makeText(applicationContext, "" + e.message, Toast.LENGTH_SHORT).show()
            }

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {


            storedVerificationId = verificationId
            resendToken = token


        }
    }

    private fun verifyVerification(codeUser: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, codeUser)
        signUp(credential)
    }

    private fun signUp(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this) { task ->

            if (task.isSuccessful){
                progress_verify.visibility = View.GONE
                if (nBool==true){
                    val user=FirebaseAuth.getInstance().currentUser
                    val uid=user!!.uid
                    dataReference=FirebaseDatabase.getInstance().reference.child("Staff").child("JE").child("Assistants")
                    val staffMap=HashMap<String,Any>()
                    staffMap["name"]=staffName.toString()
                    staffMap["uid"]=uid.toString()
                    staffMap["phone"]=phone.toString()
                    dataReference.child(uid).updateChildren(staffMap)
                    dataReference.child(staffUid.toString()).removeValue().addOnCompleteListener {
                        if (task.isSuccessful){
                            Toast.makeText(this,"success of JE",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@MainActivity,Home::class.java))
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(this,"success of staff",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity,Home::class.java))
                    finish()
                }

            }
        }

    }
}