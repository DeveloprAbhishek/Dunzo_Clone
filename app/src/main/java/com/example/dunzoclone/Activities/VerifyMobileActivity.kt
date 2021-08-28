package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dunzoclone.DataModels.User
import com.example.dunzoclone.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_verify_mobile.*
import java.util.concurrent.TimeUnit

class VerifyMobileActivity : AppCompatActivity() {
    private var phoneNumber: String? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var options: PhoneAuthOptions
    private var verificationCode: String? = null
    private var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_mobile)
        auth = Firebase.auth
        getIntentData()
        sendVerificationCode()

        btnVerify.setOnClickListener {
            if (etOTP.text.isEmpty()){
                etOTP.error = "Invalid OTP"
            }else{
                verifyCode(etOTP.text.toString())
            }
        }
    }

    private fun getIntentData() {

        phoneNumber = intent.getStringExtra("phoneNumber").toString()
        tvEnterOTP.text = phoneNumber
    }

    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationCode = p0

            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                val code: String? = p0.smsCode
                if (code != null) {
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(
                    this@VerifyMobileActivity,
                    "verification failed - ${p0.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    private fun verifyCode(code: String) {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(verificationCode.toString(), code)
        signTheUserByCredentials(credential)
    }

    private fun signTheUserByCredentials(credential: PhoneAuthCredential) {

        val firebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult>() {
                if (it.isSuccessful) {
                    Toast.makeText(this@VerifyMobileActivity, "Otp verification Successful", Toast.LENGTH_LONG).show()

                    auth.currentUser?.let { it1 ->
                        addUserDetailsToDatabase(it1.uid)
                    }
                    val intent = Intent(this@VerifyMobileActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@VerifyMobileActivity, "FAIL", Toast.LENGTH_LONG).show()
                }
            })

    }

    private fun addUserDetailsToDatabase(uid: String) {
        val db = Firebase.firestore
        val user = User(tvEnterOTP.text.toString(), uid)
        db.collection("users").document(uid).set(user)
    }

    private fun sendVerificationCode() {
        if (phoneNumber != null) {
            options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91$phoneNumber")       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }
}