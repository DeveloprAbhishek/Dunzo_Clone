package com.example.dunzoclone.Activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.dunzoclone.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_payment_successful.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class PaymentSuccessfulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.BLACK
        setContentView(R.layout.activity_payment_successful)


        val path = "android.resource://com.example.dunzoclone/" + R.raw.pss

        val uri = Uri.parse(path)
        VideoViewPs.setVideoURI(uri)

        VideoViewPs.setOnPreparedListener { mp -> mp.start() }

        VideoViewPs.setOnCompletionListener {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
            }
        }
    }
