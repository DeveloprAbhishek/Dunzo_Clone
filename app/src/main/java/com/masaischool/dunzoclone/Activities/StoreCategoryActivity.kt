package com.masaischool.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masaischool.dunzoclone.Adapters.BestSellerAdapter
import com.masaischool.dunzoclone.Adapters.StoreCategoryAdapter
import com.masaischool.dunzoclone.ClickLitener.StoreCatItemClickListener
import com.masaischool.dunzoclone.DataModels.Categories
import com.masaischool.dunzoclone.DataModels.ProductCategory
import com.masaischool.dunzoclone.DataModels.Products
import com.masaischool.dunzoclone.DataModels.Store
import com.masaischool.dunzoclone.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_store_category.*
import kotlinx.android.synthetic.main.activity_stores.*
import java.util.ArrayList

class StoreCategoryActivity : AppCompatActivity(), StoreCatItemClickListener{
    private val tag = "StoreCategoryActivity"

    private lateinit var storeName: String
    private lateinit var store_id: String

    //firestore
    private val db = Firebase.firestore
    private var bestSellerList = ArrayList<Products>()
    private val bestSellerListRef = db.collection("products");

    private var listOfStoreCat = ArrayList<Categories>()
    private val storeCategoryRef = db.collection("stores");
    private lateinit var storeCategoryListener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_category)
        initViews()
    }

    private fun initViews() {
        //store details
        storeName = intent.getStringExtra("storeName").toString()
        tvStoreName.text = storeName
        tvStoreLocation.text = intent.getStringExtra("storeAddress").toString()
        store_id = intent.getStringExtra("store_id").toString()

        //getDataFromFirebase()
    }

    override fun onStart() {
        super.onStart()
        getSellerData()
        getCategoryData()
    }

    private fun getCategoryData() {
        listOfStoreCat.clear()
        storeCategoryListener = storeCategoryRef.document(store_id).addSnapshotListener { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                var storeListObject = snapshot.toObject(Store::class.java)
                listOfStoreCat = storeListObject?.categories as ArrayList<Categories>
                setAdapter()
                Log.d(tag, "$snapshot dataCategories: ${listOfStoreCat[0].category_name}")
            } else {
                Log.d(tag, "${e?.message} data: null")
            }
        }
    }


    private fun getSellerData() {
        bestSellerList.clear()
        bestSellerListRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                for (doc in snapshot) {
                    Log.d("abhishek", doc.data.toString())
                    var storeListObject = doc.toObject(Products::class.java)
                    bestSellerList.add(storeListObject)
                }
                setBestSellerAdapter()
            } else {
                Log.d(tag, "Current data: null")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        storeCategoryListener.remove()
    }


    private fun setBestSellerAdapter() {
        recyclerBestSeller.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false)
        recyclerBestSeller.adapter = BestSellerAdapter(bestSellerList)
    }

    private fun setAdapter() {
        recyclerviewStoreCategory.layoutManager = GridLayoutManager(this, 3)
        recyclerviewStoreCategory.adapter = StoreCategoryAdapter(listOfStoreCat, this)
    }

    override fun onItemClickListener(productCategory: Categories, position: Int) {
        val intent = Intent(this@StoreCategoryActivity, ProductActivity::class.java)

        intent.putExtra("storeName", storeName)
        intent.putExtra("storeId", store_id)
        intent.putExtra("productCategoryName", productCategory.category_name)
        intent.putExtra("productCategoryImage", productCategory.category_image)
        startActivity(intent)
    }
}