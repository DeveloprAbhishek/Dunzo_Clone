package com.masai.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.dunzoclone.R
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


        tvUseCurrentLocation.setOnClickListener {
            startActivity(Intent(this@LocationActivity, MapActivity::class.java))
        }
    }
}