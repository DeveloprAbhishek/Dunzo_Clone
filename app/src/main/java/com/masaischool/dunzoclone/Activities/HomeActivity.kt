package com.masaischool.dunzoclone.Activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_home.*

import com.masaischool.dunzoclone.R
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.masaischool.dunzoclone.DataModels.Products
import com.masaischool.dunzoclone.Fragments.*
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    //firestore
    private var isOrderHasData: Boolean = false
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val orderRef = db.collection("users");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        onLocationRequestPermission()
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        bottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        getOrderData()
    }

    private fun getOrderData() {
        auth.currentUser?.uid?.let {
            orderRef.document(it).collection("orderItem")
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null && !snapshot.isEmpty) {
                        isOrderHasData = true
                    }
                }
        }
    }
    private fun onLocationRequestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        } else {
            onShowToastMessage("PERMISSION GRANTED")
        }
    }

    private fun bottomNavigation() {
        menu_bottom.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menu_home -> temp = HomeFragment()
                R.id.menu_search -> temp = SearchFragment()
                R.id.menu_orders -> {
                    temp = if(isOrderHasData) {
                        OrdersFragment()
                    } else {
                        EmptyOrderFragment()
                    }
                }
                R.id.menu_dunzo_cash -> temp = DunzoCashFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp).commit()
            }
            true
        })
    }

    private fun onShowToastMessage(str: String) {
        Toast.makeText(this@HomeActivity, str, Toast.LENGTH_SHORT).show()
    }
}