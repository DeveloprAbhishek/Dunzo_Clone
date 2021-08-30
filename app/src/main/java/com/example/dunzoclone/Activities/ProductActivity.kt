package com.example.dunzoclone.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dunzoclone.Adapters.ProductAdapter
import com.example.dunzoclone.ClickLitener.ProductItemClickListener
import com.example.dunzoclone.DataModels.ProductModel
import com.example.dunzoclone.DataModels.Products
import com.example.dunzoclone.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_store_category.*
import java.util.ArrayList

class ProductActivity : AppCompatActivity(), ProductItemClickListener {
    //₹
    private val tag = "ProductActivity"

    private lateinit var auth: FirebaseAuth

    private var cartList = ArrayList<ProductModel>()

    private lateinit var storeId: String
    private lateinit var storeName: String
    private lateinit var productCatName: String
    private lateinit var productCatImage: String

    private var cartTotalPrice: Int = 0
    private var cartTotalItem: Int = 0

    //firestore
    private var listOfProducts = ArrayList<Products>()
    private val db = Firebase.firestore
    private val productsRef = db.collection("products");
    private val cartRef = db.collection("users");
    private lateinit var productListener: ListenerRegistration
    private var cartProductId = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        auth = Firebase.auth
        initViews()
        getCartProductId()
    }

    private fun getCartProductId() {
        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("cartItem").get().addOnSuccessListener { document ->
                for (doc in document) {
                    cartProductId.put(doc.data["product_id"].toString(), doc.data["product_id"].toString())
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        getProducts()
        getCartDetail()
    }

    private fun getCartDetail() {
        auth.currentUser?.uid?.let {
            cartRef.document(it).get().addOnSuccessListener { document->
                if (document != null) {
                    cartTotalPrice =  document.data?.get("cartTotal").toString().toInt()
                    cartTotalItem = document.data?.get("totalItem").toString().toInt()
                    if(cartTotalItem > 0) {
                        bottomBar.visibility = View.VISIBLE
                        tvCartItemQty.text  = "${cartTotalItem.toString()} Item"
                        tvCartPrice.text = "₹${cartTotalPrice.toString()}"
                    }
                } else {
                    Log.d(tag, "No such document")
                }
            }
        }
    }

    private fun getProducts() {
        listOfProducts.clear()
        productListener = productsRef.whereEqualTo("store_id", storeId)
            .whereEqualTo("category", productCatName)
            .addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                for (doc in snapshot) {
                    var storeListObject = doc.toObject(Products::class.java)
                    listOfProducts.add(storeListObject)
                }
                setAdapter()

            } else {
                Log.d(tag, "Current data: null")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        productListener.remove()
    }

    private fun initViews() {
        storeId = intent.getStringExtra("storeId").toString()
        storeName = intent.getStringExtra("storeName").toString()
        productCatName = intent.getStringExtra("productCategoryName").toString()
        productCatImage = intent.getStringExtra("productCategoryImage").toString()
        tvPCName.text = productCatName
        Glide.with(ivPCImage).load(productCatImage).into(ivPCImage)

        btnCardBtn.setOnClickListener{
            var intent = Intent(this@ProductActivity, CartActivity::class.java)
            intent.putExtra("storeId", storeId)
            intent.putExtra("storeName", storeName)
            startActivity(intent)
        }

        ibBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setAdapter() {
        productRecyclerView.layoutManager = LinearLayoutManager(this)
        productRecyclerView.adapter = ProductAdapter(listOfProducts, this, cartProductId)
    }

    override fun onItemClickListener(productModel: Products, storePosition: Int) {
        val intent = Intent(this@ProductActivity, ProductDetailActivity::class.java)
        intent.putExtra("storeName", storeName)
        intent.putExtra("productName", productModel.name)
        intent.putExtra("productImage", productModel.image)
        intent.putExtra("productPrice", productModel.price)
        intent.putExtra("productQuantity", productModel.quantity)
        startActivity(intent)
    }

    override fun onAddButtonClick(productModel: Products, storePosition: Int) {
        addDataToCart(productModel, storePosition)
        bottomBar.visibility = View.VISIBLE
        updateDataToBottomBar(productModel, storePosition)
    }

    override fun onPlusButtonClick(productModel: Products, storePosition: Int) {

    }

    override fun onMinusButtonClick(productModel: Products, storePosition: Int) {
        cartTotalPrice -= productModel.price.toString().toInt()
        cartTotalItem -= productModel.quantity.toString().toInt()

        tvCartItemQty.text  = "${cartTotalItem.toString()} Item"
        tvCartPrice.text = "₹${cartTotalPrice.toString()}"

        val updateCartTotalPrice = hashMapOf("cartTotal" to cartTotalPrice)
        val updateCartTotalItem = hashMapOf("totalItem" to cartTotalItem)

        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("cartItem").document(productModel.product_id).delete()
            cartRef.document(it).set(updateCartTotalPrice, SetOptions.merge())
            cartRef.document(it).set(updateCartTotalItem, SetOptions.merge())
        }
    }

    private fun addDataToCart(productModel: Products, position: Int) {
        auth.currentUser?.uid?.let {
            cartRef.document(it).collection("cartItem").document(productModel.product_id).set(productModel)
        }
    }

    private fun updateDataToBottomBar(productModel: Products, storePosition: Int) {
        cartTotalPrice += (productModel.price).toString().toInt()
        cartTotalItem += (productModel.quantity).toString().toInt()

        tvCartItemQty.text  = "${cartTotalItem.toString()} Item"
        tvCartPrice.text = "₹${cartTotalPrice.toString()}"

        val updateCartTotalPrice = hashMapOf("cartTotal" to cartTotalPrice)
        val updateCartTotalItem = hashMapOf("totalItem" to cartTotalItem)

        auth.currentUser?.uid?.let {
            cartRef.document(it).set(updateCartTotalPrice, SetOptions.merge())
            cartRef.document(it).set(updateCartTotalItem, SetOptions.merge())
        }
    }

    fun onShowToastMessage(str: String) {
        Toast.makeText(this@ProductActivity, str, Toast.LENGTH_SHORT).show()
    }
}