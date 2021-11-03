package com.masai.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.dunzoclone.Adapters.OrderAdapter
import com.masai.dunzoclone.DataModels.Products
import com.masai.dunzoclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_order.*
import java.util.ArrayList

class OrderActivity : AppCompatActivity() {
    private val tag = "OrderActivity"
    private var orderList = ArrayList<Products>()
    private lateinit var auth: FirebaseAuth

    //firestore
    private val db = Firebase.firestore
    private val cartRef = db.collection("users");
    private lateinit var store_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        auth = Firebase.auth

        ibBackButton.setOnClickListener {
            startActivity(Intent(this@OrderActivity, HomeActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        getOrderData()
    }

    private fun getOrderData() {
        orderList.clear()
        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("orderItem")
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null && !snapshot.isEmpty) {
                        for (doc in snapshot) {
                            Log.d(tag, doc.data.toString())
                            var storeListObject = doc.toObject(Products::class.java)
                            orderList.add(storeListObject)
                        }
                        setAdapter()
                    } else {
                        Log.d(tag, "Current data: null")
                    }
                }
        }
    }

    private fun setAdapter() {
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
        orderRecyclerView.adapter = OrderAdapter(orderList)
    }
}