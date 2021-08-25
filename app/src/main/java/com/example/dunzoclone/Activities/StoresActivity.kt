package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dunzoclone.Adapters.StoreListAdapter
import com.example.dunzoclone.ClickLitener.StoreItemClickListener
import com.example.dunzoclone.DataModels.ProductCategory
import com.example.dunzoclone.DataModels.StoreListModel
import com.example.dunzoclone.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_stores.*
import java.util.ArrayList

class StoresActivity : AppCompatActivity(), StoreItemClickListener {
    private lateinit var categoryTitle: String
    private var storeList = ArrayList<StoreListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)
        initViews()
    }

    private fun initViews() {
        categoryTitle = intent.getStringExtra("storeCategoryName").toString()
        tvCategoryTitle.text = categoryTitle
        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {
        val database = Firebase.database
        var categoryRef =database.getReference("category").child(categoryTitle).child("store")

        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("abhishek", snapshot.toString())
                val genericTypeIndicator = object : GenericTypeIndicator<ArrayList<StoreListModel>>() {};
                storeList = snapshot.getValue(genericTypeIndicator) as ArrayList<StoreListModel>
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setAdapter() {
        storeRecyclerView.layoutManager = LinearLayoutManager(this)
        storeRecyclerView.adapter = StoreListAdapter(storeList, this)
    }

    private fun showToast(str:String) {
        Toast.makeText(this@StoresActivity, str, Toast.LENGTH_SHORT).show()
    }


    override fun onItemClickListener(storeListModel: StoreListModel, position: Int) {
        val intent = Intent(this@StoresActivity, StoreCategoryActivity::class.java)
        intent.putExtra("storePosition", position)
        intent.putExtra("storeCategoryName", categoryTitle)

        intent.putExtra("storeName", storeList[position].store_name)
        intent.putExtra("storeAddress", storeList[position].address)
        intent.putExtra("storeTime", storeList[position].time)
        startActivity(intent)
    }
}