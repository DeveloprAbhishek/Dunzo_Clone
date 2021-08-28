package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.StoreListAdapter
import com.example.dunzoclone.ClickLitener.StoreItemClickListener
import com.example.dunzoclone.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_stores.*
import com.example.dunzoclone.DataModels.Store

import com.google.firebase.firestore.ListenerRegistration
import java.util.*
import kotlin.collections.ArrayList


class StoresActivity : AppCompatActivity(), StoreItemClickListener {
    private val tag = "StoreActivity"

    private var listOfStores = ArrayList<Store>()
    private var storeSize = 0

    private val db = Firebase.firestore
    private val storeRef = db.collection("stores");
    private lateinit var storeListener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)
        initViews()
    }

    private fun initViews() {
        val categoryTitle = intent.getStringExtra("storeCategoryName").toString()
        tvCategoryTitle.text = "Order $categoryTitle"
    }


    override fun onStart() {
        super.onStart()
        getAllStore()
    }


    private fun getAllStore() {
        listOfStores.clear()
        storeListener = storeRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                tvStoreSize.text = snapshot.size().toString() + " Stores"
                for (doc in snapshot) {
                    Log.d(tag, doc.data["address"].toString())

                    var storeListObject = doc.toObject(Store::class.java)

                    listOfStores.add(storeListObject)
                    Log.d(tag, "Current data: ${doc.data}")
                }
                setAdapter()
                Log.d(tag, "storeList: ${listOfStores}")
                Log.d(tag, "storeList: ${listOfStores[0].categories}")

            } else {
                Log.d(tag, "Current data: null")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        storeListener.remove()
    }

    private fun setAdapter() {
        storeRecyclerView.layoutManager = LinearLayoutManager(this)
        storeRecyclerView.adapter = StoreListAdapter(listOfStores, this)
    }

    override fun onItemClickListener(storeListModel: Store, position: Int) {
        val intent = Intent(this@StoresActivity, StoreCategoryActivity::class.java)
        intent.putExtra("store_id", storeListModel.store_id)
        intent.putExtra("storeName", storeListModel.store_name)
        intent.putExtra("storeAddress", storeListModel.address)
        startActivity(intent)
    }

    private fun showToast(str: String) {
        Toast.makeText(this@StoresActivity, str, Toast.LENGTH_SHORT).show()
    }

}