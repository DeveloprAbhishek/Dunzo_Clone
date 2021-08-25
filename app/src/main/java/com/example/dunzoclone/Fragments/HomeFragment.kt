package com.example.dunzoclone.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dunzoclone.Activities.AdminActivity
import com.example.dunzoclone.Activities.EmptyCartActivity
import com.example.dunzoclone.Activities.LocationActivity
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvToolbarLocation.setOnClickListener {
            val intent = Intent(context, LocationActivity::class.java)
            startActivity(intent)
        }

        ivToolbarCart.setOnClickListener {
            val intent = Intent(context, EmptyCartActivity::class.java)
            startActivity(intent)
        }

        ivToolbarProfile.setOnClickListener {
            val intent = Intent(context, AdminActivity::class.java)
            startActivity(intent)
        }
    }
}