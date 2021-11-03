package com.masai.dunzoclone.Activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.masai.dunzoclone.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.BLACK
        setContentView(R.layout.activity_splash_screen)


        val path = "android.resource://com.masai.dunzoclone/" + R.raw.splash

        val uri = Uri.parse(path)
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener { mp -> mp.start() }

        videoView.setOnCompletionListener {
            Log.d("abhishek", auth.currentUser.toString())
            if (auth.currentUser != null) {
                val intent = Intent(this@SplashScreen, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

}