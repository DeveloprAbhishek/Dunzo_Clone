package com.masaischool.dunzoclone.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.masaischool.dunzoclone.Activities.OrderDetailActivity
import com.masaischool.dunzoclone.Activities.OrderDetailsActivity
import com.masaischool.dunzoclone.Adapters.OrderAdapter
import com.masaischool.dunzoclone.DataModels.Products
import com.masaischool.dunzoclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.orderRecyclerView
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.fragment_orders.view.*
import java.util.ArrayList

class OrdersFragment : Fragment(R.layout.fragment_orders) {
    private var orderList = ArrayList<Products>()
    private lateinit var auth: FirebaseAuth

    //firestore
    private val db = Firebase.firestore
    private val orderRef = db.collection("users");

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
    }

    override fun onResume() {
        super.onResume()
        getOrderData()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth.currentUser?.uid?.let {
            orderRef.document(it).get().addOnSuccessListener{ doc ->
                view.tvPriceOrder.text = doc.data?.get("orderPrice").toString()
                view.tvCustomerAddressOrder.text = doc.data?.get("address").toString()
                Log.d("abhishek", doc.data?.get("address").toString())
                Log.d("abhishek", doc.data?.get("orderPrice").toString())
            }
        }
        btnTrackOrder.setOnClickListener {
            startActivity(Intent(context, OrderDetailsActivity::class.java))
        }
    }

    private fun getOrderData() {
        orderList.clear()
        auth.currentUser?.uid?.let {
            orderRef.document(it).collection("orderItem")
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
        orderRecyclerView.layoutManager = LinearLayoutManager(context)
        orderRecyclerView.adapter = OrderAdapter(orderList)
    }
}