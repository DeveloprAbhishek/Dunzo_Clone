package com.example.dunzoclone.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dunzoclone.Activities.AdminActivity
import com.example.dunzoclone.Activities.EmptyCartActivity
import com.example.dunzoclone.Activities.LocationActivity
import com.example.dunzoclone.Activities.StoresActivity
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //Toolbar Views
        ivToolbarProfile.setOnClickListener(this)
        ivToolbarCart.setOnClickListener(this)
        tvToolbarLocation.setOnClickListener(this)

        //Categories Views
        ivFruits.setOnClickListener(this)
        ivGroceries.setOnClickListener(this)
        ivMeat.setOnClickListener(this)
        ivPickup.setOnClickListener(this)

    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivFruits -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Fruits & Vegetables")
                startActivity(intent)
            }
            R.id.ivGroceries -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Daily Grocery")
                startActivity(intent)
            }
            R.id.ivMeat -> {
                val intent = Intent(context, StoresActivity::class.java)
                intent.putExtra("storeCategoryName", "Meat and Fish")
                startActivity(intent)
            }
            R.id.ivPickup -> {
                Toast.makeText(context, "PickUp", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.ivToolbarProfile -> {
                startActivity(Intent(context, AdminActivity::class.java))
            }
            R.id.ivToolbarCart -> {
                startActivity(Intent(context, EmptyCartActivity::class.java))
            }
            R.id.tvToolbarLocation -> {
                startActivity(Intent(context, LocationActivity::class.java))
            }
        }
    }

}