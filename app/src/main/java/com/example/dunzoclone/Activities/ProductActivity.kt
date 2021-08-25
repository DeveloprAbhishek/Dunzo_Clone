package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dunzoclone.Adapters.ProductAdapter
import com.example.dunzoclone.ClickLitener.ProductItemClickListener
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
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_store_category.*
import java.util.ArrayList

class ProductActivity : AppCompatActivity(), ProductItemClickListener {
    private lateinit var auth: FirebaseAuth

    private lateinit var categoryTitle: String
    private var storePosition: Int = 0
    private var productCategoryPosition: Int = 0
    private var particularProductList = ArrayList<ProductModel>()

    private lateinit var storeName: String
    private lateinit var productCatName: String
    private lateinit var productCatImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        auth = Firebase.auth
        initViews()
    }

    private fun initViews() {
        storePosition = intent.getIntExtra("storePosition", 0)
        productCategoryPosition = intent.getIntExtra("productCategoryPosition", 0)
        categoryTitle = intent.getStringExtra("storeCategoryName").toString()

        storeName = intent.getStringExtra("storeName").toString()
        productCatName = intent.getStringExtra("productCategoryName").toString()
        productCatImage = intent.getStringExtra("productCategoryImage").toString()
        tvPCName.text = productCatName
        Glide.with(ivPCImage).load(productCatImage).into(ivPCImage)
        //tvStoreName.text = storeName

        btnCardBtn.setOnClickListener{

        }

        getDataFromFirebase()


    }

    private fun getDataFromFirebase() {
        val database = Firebase.database
        var categoryRef = database.getReference("category").child(categoryTitle).child("store")
            .child(storePosition.toString()).child("productCategory")
            .child(productCategoryPosition.toString()).child("specificProductCat")

        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("abhishek", snapshot.toString())
                val genericTypeIndicator =
                    object : GenericTypeIndicator<List<ProductModel?>?>() {};
                particularProductList =
                    snapshot.getValue(genericTypeIndicator) as ArrayList<ProductModel>
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    private fun setAdapter() {
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = ProductAdapter(particularProductList, this)
    }

    override fun onItemClickListener(productModel: ProductModel, storePosition: Int) {
        val intent = Intent(this@ProductActivity, ProductDetailActivity::class.java)
        intent.putExtra("storeName", storeName)
        intent.putExtra("productName", productModel.name)
        intent.putExtra("productImage", productModel.image)
        intent.putExtra("productPrice", productModel.price)
        intent.putExtra("productQuantity", productModel.quantity)
        startActivity(intent)
    }

    override fun onPlusButtonClick(productModel: ProductModel, storePosition: Int) {
        addDataToCart(productModel)
        bottomBar.visibility = View.VISIBLE
    }

    private fun addDataToCart(productModel: ProductModel) {
        var userId = auth.currentUser?.uid
        val database = Firebase.database
        database.getReference("df").child("sdf").push()
        var cartRef = userId?.let { database.getReference("users").child(it).child("cartItem") }
        if (cartRef != null) {
            cartRef.push().setValue(productModel)
        }
    }
}