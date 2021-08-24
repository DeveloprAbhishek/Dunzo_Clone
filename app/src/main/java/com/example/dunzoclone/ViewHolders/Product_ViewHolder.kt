//package com.example.dunzoclone.ViewHolders
//
//import android.view.View
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.dunzoclone.DataModels.Product_Model
//import kotlinx.android.synthetic.main.product_item_layout.view.*
//import kotlinx.android.synthetic.main.product_item_layout.view.ivProductImage
//import kotlinx.android.synthetic.main.store_item_layout.view.*
//
//class Product_ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//    fun setProductData(productList : Product_Model){
//        itemView.apply {
//            tvProductName.text = productList.product_name
//            tvProductDesc.text = productList.product_desc
//            tvProductPrice.text = productList.price
//
//            Glide.with(ivProductImage).load(productList.image_url).into(ivProductImage)
//        }
//    }
//}