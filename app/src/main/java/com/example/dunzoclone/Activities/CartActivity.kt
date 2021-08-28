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
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_product.*
import org.json.JSONObject
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
    private lateinit var store_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        Checkout.preload(applicationContext)

        auth = Firebase.auth
        getIntentData()
        getCartTotal()

        ivBack.setOnClickListener {
            onBackPressed()
        }

        btnPay.setOnClickListener {
            updateOrder()
            startActivity(Intent(this, PaymentActivity::class.java))
        }
    }

    private fun getIntentData() {
        store_id = intent.getStringExtra("storeId").toString()
    }

    private fun updateOrder() {
        val updateOrderTotalPrice = hashMapOf("orderPrice" to cartTotalPrice)
        val updateOrderTotalItem = hashMapOf("orderItem" to cartTotalItem)

        val updateCartTotalPrice = hashMapOf("cartTotal" to 0)
        val updateCartTotalItem = hashMapOf("totalItem" to 0)

        auth.currentUser?.uid?.let {
            cartRef.document(it).set(updateOrderTotalPrice, SetOptions.merge())
            cartRef.document(it).set(updateOrderTotalItem, SetOptions.merge())

            cartRef.document(it).set(updateCartTotalPrice, SetOptions.merge())
            cartRef.document(it).set(updateCartTotalItem, SetOptions.merge())
        }

        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("cartItem").get().addOnSuccessListener { document ->
                for (doc in document) {
                    cartRef.document(it).collection("orderItem").add(doc.data)
                    doc.reference.delete()
                }
            }
        }
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

    private fun setAdapter() {
        recyclerCart.layoutManager = LinearLayoutManager(this)
        recyclerCart.adapter = CartAdapter(cartList)
    }

    fun onShowToastMessage(str: String) {
        Toast.makeText(this@CartActivity, str, Toast.LENGTH_SHORT).show()
    }


    //Payment using RazorPay
    private fun startPayment() {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val activity:Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","Razorpay Corp")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency","INR");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount","50000")//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentSuccess(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Log.d(tag, p1.toString())
    }
}