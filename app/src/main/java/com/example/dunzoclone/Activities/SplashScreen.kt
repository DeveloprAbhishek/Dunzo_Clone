package com.example.dunzoclone.Activities

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.widget.VideoView
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.RelativeLayout
import java.lang.Exception


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

}