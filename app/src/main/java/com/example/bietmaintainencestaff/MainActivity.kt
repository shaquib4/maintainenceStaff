package com.example.bietmaintainencestaff

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var storedVerificationId: String
    var firebaseUser: FirebaseUser? = null
    private var phone:String?="400"
    private lateinit var otp:PinView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        otp=findViewById(R.id.otp)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser
        phone=intent.getStringExtra("phone")
        sendVerification("+91$phone")


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
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
            if (task.isSuccessful){
                startActivity(Intent(this@MainActivity,Home::class.java))
                finish()
            }
        }

    }
}