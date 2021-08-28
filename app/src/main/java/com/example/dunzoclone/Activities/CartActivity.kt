package com.example.dunzoclone.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.CartAdapter
import com.example.dunzoclone.Adapters.ProductAdapter
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.DataModels.Products
import com.example.dunzoclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_product.*
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList

class CartActivity : AppCompatActivity(), PaymentResultListener {
    private val tag = "CartActivity"
    private lateinit var auth: FirebaseAuth
    private var cartList = ArrayList<Products>()
    private var cartTotalPrice: Int = 0
    private var cartTotalItem: Int = 0

    //firestore
    private val db = Firebase.firestore
    private val cartRef = db.collection("users");


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Checkout.preload(applicationContext)

        auth = Firebase.auth
        getCartTotal()

        ivBack.setOnClickListener {
            onBackPressed()
        }

        btnPay.setOnClickListener {
            startPayment()
        }
        //showCartValue()
    }

    override fun onStart() {
        super.onStart()
        getCartDetails()
    }

    private fun getCartTotal() {
        auth.currentUser?.uid?.let {
            cartRef.document(it).get().addOnSuccessListener { document ->
                if (document != null) {
                    cartTotalPrice = document.data?.get("cartTotal").toString().toInt()
                    cartTotalItem = document.data?.get("totalItem").toString().toInt()
                    tvItemTotal.text = cartTotalItem.toString()
                    tvToPay.text = cartTotalPrice.toString()
                } else {
                    Log.d(tag, "No such document")
                }
            }
        }
    }

    private fun getCartDetails() {
        cartList.clear()
        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("cartItem")
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null && !snapshot.isEmpty) {
                        for (doc in snapshot) {
                            var storeListObject = doc.toObject(Products::class.java)
                            cartList.add(storeListObject)
                        }
                        setAdapter()

                    } else {
                        Log.d(tag, "Current data: null")
                    }
                }
        }
    }

    override fun onStop() {
        super.onStop()
    }

    private fun showCartValue() {
//        val userId = auth.currentUser?.uid
//        val database = Firebase.database
//        val cartRef = userId?.let {
//            database.getReference("users").child(it).child("cartItem").addValueEventListener(object :
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    Log.d("abhishek", snapshot.value.toString())
//                        val genericTypeIndicator =
//                            object : GenericTypeIndicator<List<ProductModel?>?>() {};
//                        cartList = snapshot.getValue(genericTypeIndicator) as ArrayList<ProductModel>
//                        setAdapter()
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    onShowToastMessage("Error While clicking minus button!")
//                }
//
//            })
//        }
    }

    private fun setAdapter() {
        recyclerCart.layoutManager = LinearLayoutManager(this)
        recyclerCart.adapter = CartAdapter(cartList)
    }

    fun onShowToastMessage(str: String) {
        Toast.makeText(this@CartActivity, str, Toast.LENGTH_SHORT).show()
    }


    //Payment using RazorPay
     private fun startPayment() {

        val activity:Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","App name")
            options.put("description","Demo payment")
//You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency","INR")
            options.put("amount",cartTotalPrice*100)

            val prefill = JSONObject()
            prefill.put("email","test@razorpay.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: java.lang.Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentError(errorCode: Int, response: String?) {

            Log.e("TAG","Payment Success")

    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        Log.e("TAG","Payment Failure")
    }
}