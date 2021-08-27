package com.example.dunzoclone.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.CartAdapter
import com.example.dunzoclone.Adapters.ProductAdapter
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_product.*
import java.util.ArrayList

class CartActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var cartList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        auth = Firebase.auth

        ivBack.setOnClickListener {
            onBackPressed()
        }

        showCartValue()
    }

    private fun showCartValue() {
        val userId = auth.currentUser?.uid
        val database = Firebase.database
        val cartRef = userId?.let {
            database.getReference("users").child(it).child("cartItem").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("abhishek", snapshot.value.toString())
                        val genericTypeIndicator =
                            object : GenericTypeIndicator<List<ProductModel?>?>() {};
                        cartList = snapshot.getValue(genericTypeIndicator) as ArrayList<ProductModel>
                        setAdapter()

                }

                override fun onCancelled(error: DatabaseError) {
                    onShowToastMessage("Error While clicking minus button!")
                }

            })
        }
    }

    private fun setAdapter() {
        recyclerCart.layoutManager = LinearLayoutManager(this)
        recyclerCart.adapter = CartAdapter(cartList)
    }

    fun onShowToastMessage(str: String) {
        Toast.makeText(this@CartActivity, str, Toast.LENGTH_SHORT).show()
    }
}