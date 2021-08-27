package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.StoreCategoryAdapter
import com.example.dunzoclone.ClickLitener.StoreCatItemClickListener
import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_store_category.*
import kotlinx.android.synthetic.main.activity_stores.*
import java.util.ArrayList

class StoreCategoryActivity : AppCompatActivity(), StoreCatItemClickListener{
    private lateinit var categoryTitle: String
    private var storePosition: Int = 0
    private lateinit var storeName: String
    private var storeDetailsList = ArrayList<ProductCategory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_category)
        initViews()
    }

    private fun initViews() {
        storePosition = intent.getIntExtra("storePosition", 0)
        categoryTitle = intent.getStringExtra("storeCategoryName").toString()
        //store details
        storeName = intent.getStringExtra("storeName").toString()
        tvStoreName.text = storeName
        tvStoreLocation.text = intent.getStringExtra("storeAddress").toString()
        //categoryTitle = intent.getStringExtra("storeTime").toString()
        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {
        val database = Firebase.database
        var categoryRef = database.getReference("category").child(categoryTitle).child("store")
            .child(storePosition.toString()).child("productCategory")

        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("abhishek", snapshot.toString())
                val genericTypeIndicator =
                    object : GenericTypeIndicator<List<ProductCategory?>?>() {};
                storeDetailsList = snapshot.getValue(genericTypeIndicator) as ArrayList<ProductCategory>
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun setAdapter() {
        recyclerviewStoreCategory.layoutManager = LinearLayoutManager(this)
        recyclerviewStoreCategory.adapter = StoreCategoryAdapter(storeDetailsList, this)
    }

    override fun onItemClickListener(productCategory: ProductCategory, position: Int) {
        val intent = Intent(this@StoreCategoryActivity, ProductActivity::class.java)
        intent.putExtra("storePosition", storePosition)
        intent.putExtra("productCategoryPosition", position)
        intent.putExtra("storeCategoryName", categoryTitle)

        intent.putExtra("storeName", storeName)
        intent.putExtra("productCategoryName", productCategory.name)
        intent.putExtra("productCategoryImage", productCategory.image)
        startActivity(intent)
    }
}