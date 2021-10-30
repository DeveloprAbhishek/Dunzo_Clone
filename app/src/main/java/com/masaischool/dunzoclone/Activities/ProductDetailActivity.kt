package com.masaischool.dunzoclone.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.masaischool.dunzoclone.Adapters.ProductAdapter
import com.masaischool.dunzoclone.DataModels.ProductModel
import com.masaischool.dunzoclone.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_product_detail_screen.*
import java.util.ArrayList

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_screen)
        initViews()
    }

    private fun initViews() {
        //tvItemName.text = intent.getStringExtra("storeName")
        tvItemName.text = intent.getStringExtra("productName")
        tvItemPrice.text = intent.getStringExtra("productPrice")
        tvItemQty.text = intent.getStringExtra("productQuantity")
        Glide.with(ivItemImage).load(intent.getStringExtra("productImage")).into(ivItemImage)
    }

}